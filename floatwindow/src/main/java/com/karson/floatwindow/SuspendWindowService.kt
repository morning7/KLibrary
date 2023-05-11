package com.karson.floatwindow

import android.annotation.SuppressLint
import android.graphics.PixelFormat
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Toast
import androidx.lifecycle.LifecycleService

class SuspendWindowService: LifecycleService() {

    private lateinit var windowManager: WindowManager
    private var floatRootView: View? = null//悬浮窗View


    override fun onCreate() {
        super.onCreate()
        Log.e("SuspendWindowService", "onCreate")
        initObserve()
    }

    private fun initObserve() {
        ViewModelMain.apply {
            isVisible.observe(this@SuspendWindowService, {
                floatRootView?.visibility = if (it) View.VISIBLE else View.GONE
            })
            isShowSuspendWindow.observe(this@SuspendWindowService, {
                if (it) {
                    showWindow()
                } else {
                    if (!Utils.isNull(floatRootView)) {
                        if (!Utils.isNull(floatRootView?.windowToken)) {
                            if (!Utils.isNull(windowManager)) {
                                windowManager.removeView(floatRootView)
                            }
                        }
                    }
                }
            })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showWindow() {
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)
        val layoutParam = WindowManager.LayoutParams().apply {
            type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            } else {
                WindowManager.LayoutParams.TYPE_PHONE
            }
            format = PixelFormat.RGBA_8888
            flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            //位置大小设置
            width = WRAP_CONTENT
            height = WRAP_CONTENT
            gravity = Gravity.LEFT or Gravity.TOP
            //设置剧中屏幕显示
            x = outMetrics.widthPixels / 2 - width / 2
            y = outMetrics.heightPixels / 2 - height / 2
        }
        // 新建悬浮窗控件
        floatRootView = LayoutInflater.from(this).inflate(R.layout.activity_float_item, null)
        floatRootView?.setOnTouchListener(MyTouchListener(layoutParam, windowManager))
        // 将悬浮窗控件添加到WindowManager
        windowManager.addView(floatRootView, layoutParam)
    }

    private inner class MyTouchListener(val windowParams: WindowManager.LayoutParams, val windowManager: WindowManager) : View.OnTouchListener {
        private var lastX = 0
        private var lastY = 0

        override fun onTouch(view: View, event: MotionEvent): Boolean {
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastX = event.rawX.toInt()
                    lastY = event.rawY.toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    val nowX = event.rawX.toInt()
                    val nowY = event.rawY.toInt()
                    val offsetX = nowX - lastX
                    val offsetY = nowY - lastY
                    lastX = nowX
                    lastY = nowY
                    Log.e("MyTouchListener", "lastX $lastX winParamsX ${windowParams.x} move ${windowParams.x + lastX}")
                    windowParams.apply {
                        x += offsetX
                        y += offsetY
                    }
                    windowManager.updateViewLayout(view, windowParams)
                }
            }
            return false
        }
    }
}