package com.karson.skin.resolver

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.karson.skin.manager.SkinManager
import java.lang.ref.WeakReference

class SkinItem(private val attrs: List<SkinAttr>, private val view: WeakReference<View>) {

    fun apply() {
        val view = view.get() ?: return
        val iterator = attrs.iterator()
        while (iterator.hasNext()) {
            val skinAttr = iterator.next()
            skinAttr.apply(view)
        }
    }
}