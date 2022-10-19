package com.karson.skin.attr

import android.view.View
import android.widget.ImageView
import com.karson.skin.manager.SkinManager
import com.karson.skin.resolver.SkinAttr

class ImageAttr : SkinAttr() {
    override fun apply(view: View) {
        if (view is ImageView) {
            view.setImageDrawable(SkinManager.getDrawable(id))
        }
    }
}