package com.karson.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.karson.skin.CustomSkinAnnotation
import com.karson.skin.R

class KImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs, 0) {

    init {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.KImageView)
        val background = typeArray.getColor(R.styleable.KImageView_kbackground, Color.RED)
        setBackgroundColor(background)
        typeArray.recycle()
    }

    @CustomSkinAnnotation
    fun updateUI(background: Int) {
        setBackgroundColor(background)
    }
}