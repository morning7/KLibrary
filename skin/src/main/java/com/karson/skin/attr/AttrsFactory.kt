package com.karson.skin.attr

import com.karson.skin.resolver.SkinAttr

object AttrsFactory {

    fun create(id: Int, attrName: String, entryName: String, typeName: String): SkinAttr? {
        var skinAttr: SkinAttr? = null
        when(attrName) {
            "background" -> {
                skinAttr = BackgroundAttr()
            }
            "textColor" -> {
                skinAttr = TextAttr()
            }
            "src" -> {
                skinAttr = ImageAttr()
            }
            "kbackground" -> {
                skinAttr = CustomAttr()
            }
            else -> {
                null
            }
        }
        skinAttr?.let {
            skinAttr.id = id
            skinAttr.attrName = attrName
            skinAttr.entryName = entryName
            skinAttr.typeName = typeName
        }
        return skinAttr
    }
}