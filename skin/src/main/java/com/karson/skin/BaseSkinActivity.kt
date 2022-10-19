package com.karson.skin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karson.skin.manager.SkinManager
import com.karson.skin.resolver.SkinInflaterFactory

/**
 * 继承AppCompatActivity会报错：A factory has already been set on this LayoutInflater
 */
open class BaseSkinActivity : AppCompatActivity(), ISkinUpdate {
    private lateinit var skinInflaterFactory: SkinInflaterFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        skinInflaterFactory = SkinInflaterFactory()
        layoutInflater.factory2 = skinInflaterFactory
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        SkinManager.attach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        SkinManager.detach(this)
    }

    override fun onThemeUpdate() {
        skinInflaterFactory.apply()
    }
}