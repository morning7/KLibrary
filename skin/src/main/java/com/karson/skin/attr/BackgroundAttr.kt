package com.karson.skin.attr

import android.view.View
import com.karson.skin.manager.SkinManager
import com.karson.skin.resolver.SkinAttr

class BackgroundAttr : SkinAttr() {
    override fun apply(view: View) {
        view.setBackgroundColor(SkinManager.getColor(id))
    }
}