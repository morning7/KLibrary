package com.karson.skin

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.karson.skin.resolver.SkinInflaterFactory

/**
 * 继承AppCompatActivity会报错：A factory has already been set on this LayoutInflater
 */
open class BaseSkinActivity : FragmentActivity() {
    private lateinit var skinInflaterFactory: SkinInflaterFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skinInflaterFactory = SkinInflaterFactory()
        layoutInflater.factory = skinInflaterFactory
    }

    fun onThemeUpdate() {
        skinInflaterFactory.apply()
    }
}