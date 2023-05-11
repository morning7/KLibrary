package com.karson.klibrary.translucentbar

import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.karson.klibrary.databinding.ActivityTranslucentBarBinding

class TranslucentBarsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTranslucentBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslucentBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = Color.TRANSPARENT
        WindowCompat.setDecorFitsSystemWindows(window, false)
        //改变状态栏文字和图标的颜色（根据顶部布局的背景色来决定）
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightNavigationBars = false

            //Deprecated
//            binding.root.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        ViewCompat.setOnApplyWindowInsetsListener(binding.tv
        ) { view, windowInsets ->
            val params = view.layoutParams as FrameLayout.LayoutParams
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            params.topMargin = insets.top
            windowInsets
        }

        //修改状态栏字体颜色
//        val wic = ViewCompat.getWindowInsetsController(window.decorView)
//        wic?.isAppearanceLightStatusBars = true

    }
}