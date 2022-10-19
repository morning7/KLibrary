package com.karson.skin.resolver

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.karson.skin.attr.AttrsFactory
import java.lang.Exception
import java.lang.ref.WeakReference

private const val TAG = "SkinInflaterFactory"

/**
 * 1.检查皮肤开关已启用。
 * 2.遍历布局，生成View。
 * 3.解析View属性集，若资源为@开头，获取资源ID、资源名、资源类型，工厂类生成对应属性类。
 * 4.若皮肤属性不为空且皮肤已加载，SkinItem遍历属性实现换肤。
 */
class SkinInflaterFactory : LayoutInflater.Factory2 {

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        if (!skinEnable(attrs)) {
            return null
        }

        var view: View?
        Log.i(TAG, name)
        try {
            if (-1 == name.indexOf(".")) {
                /**
                 * System View / ViewGroup
                 * android.view
                 * android.widget
                 * android.webkit
                 */
                view = if ("View" == name) {
                    LayoutInflater.from(context).createView(name, "android.view.", attrs)
                } else {
                    LayoutInflater.from(context).createView(name, "android.widget.", attrs)
                }
            } else {
                /**
                 * Custom View is full name
                 */
                view = LayoutInflater.from(context).createView(name, null, attrs)
            }
            parseSkinAttr(context, attrs, view)
        } catch (e: ClassNotFoundException) {
            view = null
        }
        return view
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
       return null
    }

    private fun skinEnable(attrs: AttributeSet): Boolean {
        return attrs.getAttributeBooleanValue(SkinConfig.NAME_SPACE, SkinConfig.ATTR_SKIN_ENABLE, false)
    }

    /**
     * background:@2131034200
     * src:@2131492864
     * textColor:@2131034145
     */
    private val skinItemList: ArrayList<SkinItem> by lazy {
        ArrayList()
    }

    /**
     * 原理：更换View/ViewGroup的属性资源(background,src,textColor)
     * 1.layoutInflater设置自定义皮肤factory
     * 2.遍历页面下View/ViewGroup元素和属性集
     * 3.判断是否需要换肤(skin:enable="true")
     * 4.判断属性值是否为"@resourceId"格式
     * 5.添加属性值到数组中，供后面使用
     * 6.换肤操作调用自定义皮肤factory的apply方法
     * 7.判断是否有background,src,textColor等颜色或图标相关属性
     * 8.为页面上的元素集重新设置资源
     */
    private fun parseSkinAttr(context: Context, attrs: AttributeSet, view: View) {
        val skinAttrList = ArrayList<SkinAttr>()
        for (i in 0 until attrs.attributeCount) {
            val attrName = attrs.getAttributeName(i)
            val attrValue = attrs.getAttributeValue(i)
            Log.i(TAG, "$attrName:$attrValue")
            if (attrValue.startsWith("@")) {
                try {
                    val id = Integer.parseInt(attrValue.substring(1))
                    val entryName = context.resources.getResourceEntryName(id)
                    val typeName = context.resources.getResourceTypeName(id)
                    Log.i(TAG, "$entryName:$typeName")
                    /**
                     * 标记需要换肤的view和对应的属性
                     */
                    val skinAttr = AttrsFactory.create(id, attrName, entryName, typeName)
                    skinAttr?.let {
                        skinAttrList.add(skinAttr)
                    }
                } catch (e: Exception) {
                    //NumberFormatException,NotFoundException
                    e.printStackTrace()
                }
            }
        }

        if (skinAttrList.isNotEmpty()) {
            val skinItem = SkinItem(skinAttrList, WeakReference(view))
            skinItemList.add(skinItem)
            skinItem.apply()
        }
    }

    fun apply() {
        val iterator = skinItemList.iterator()
        while (iterator.hasNext()) {
            val skinItem = iterator.next()
            skinItem.apply()
        }
    }
}