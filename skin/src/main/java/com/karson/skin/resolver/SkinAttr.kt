package com.karson.skin.resolver

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.karson.skin.R
import java.lang.ref.WeakReference

class SkinAttr(private val attrName: String, private val view: WeakReference<View>) {

    fun apply() {
        val view = view.get() ?: return
        when(attrName) {
            "background" -> {
                view.setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.black))
            }
            "kbackground" -> {
                view.setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.white))
            }
            "textColor" -> {
                if (view is TextView) {
                    view.setTextColor(ContextCompat.getColor(view.context, android.R.color.white))
                }
            }
            "src" -> {
                if (view is ImageView) {
//                    view.setImageResource(R.drawable.alarm_dark)
                }
            }

        }
    }

}