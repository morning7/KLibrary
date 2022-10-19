package com.karson.skin.attr

import android.view.View
import com.karson.skin.CustomSkinAnnotation
import com.karson.skin.manager.SkinManager
import com.karson.skin.resolver.SkinAttr

class CustomAttr : SkinAttr() {
    override fun apply(view: View) {
        val methods = view.javaClass.declaredMethods
        val iterator = methods.iterator()
        while (iterator.hasNext()) {
            val method = iterator.next()
            if (method.isAnnotationPresent(CustomSkinAnnotation::class.java)) {
                if ("color" == typeName) {
                    val color = SkinManager.getColor(id)
                    method.invoke(view, color)
                }
            }
        }
    }
}