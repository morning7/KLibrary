package com.karson.skin.attr

import android.view.View
import android.widget.TextView
import com.karson.skin.manager.SkinManager
import com.karson.skin.resolver.SkinAttr

class TextAttr : SkinAttr() {
    override fun apply(view: View) {
        if (view is TextView) {
            view.setTextColor(SkinManager.getColor(id))
        }
    }
}