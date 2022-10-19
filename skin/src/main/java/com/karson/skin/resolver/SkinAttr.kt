package com.karson.skin.resolver

import android.view.View

abstract class SkinAttr {
    var id: Int = 0
    var attrName: String = ""
    var entryName: String = ""
    var typeName: String = ""

    abstract fun apply(view: View)
}