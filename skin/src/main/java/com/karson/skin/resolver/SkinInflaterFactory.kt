package com.karson.skin.resolver

import android.content.Context
import android.preference.PreferenceManager
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import java.lang.Exception
import java.lang.ref.WeakReference

private const val TAG = "SkinInflaterFactory"

class SkinInflaterFactory : LayoutInflater.Factory {
    private val skinAttrs: ArrayList<SkinAttr> by lazy {
        ArrayList()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        var view: View?
//        Log.i(TAG, name)
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
            parseSkinAttr(attrs, context, view)
        } catch (e: ClassNotFoundException) {
            view = null
        }
        return view
    }

    /**
     * background:@2131034200
     * src:@2131492864
     * textColor:@2131034145
     */
    private fun parseSkinAttr(attrs: AttributeSet, context: Context, view: View) {
        if (!skinEnable(attrs)) {
            return
        }
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
                    val skinAttr = SkinAttr(attrName, WeakReference(view))
                    skinAttrs.add(skinAttr)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun skinEnable(attrs: AttributeSet): Boolean {
        return attrs.getAttributeBooleanValue(SkinConstant.NAME_SPACE, SkinConstant.ATTR, false)
    }

    fun apply() {
        for (i in 0 until skinAttrs.size) {
            skinAttrs[i].apply()
        }
    }
}