package com.karson.floatwindow

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.*
import android.widget.ImageView

class FloatWindowService : Service() {

    companion object {
        private const val TAG = "FloatWindowService"
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate")
        showFloatWindow()
    }

    /**
     * 检查权限
     * 创建浮窗view
     * 创建windowManager
     * windowManager添加view
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun showFloatWindow() {
        val windowManager = getSystemService(Activity.WINDOW_SERVICE) as WindowManager
        val params = WindowManager.LayoutParams().apply {
            width = ViewGroup.LayoutParams.WRAP_CONTENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
            type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            } else {
                WindowManager.LayoutParams.TYPE_PHONE
            }
            //不加这句window会消费整个屏幕事件
            flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        }
        val imgView = ImageView(this)
        imgView.setImageResource(R.drawable.dog)
        imgView.setOnTouchListener(MyTouchListener(windowManager, params))
        windowManager.addView(imgView, params)
    }

    private class MyTouchListener(private val windowManager: WindowManager, private val windowParams: WindowManager.LayoutParams) : View.OnTouchListener {
        private var lastX = 0
        private var lastY = 0

        override fun onTouch(view: View, event: MotionEvent): Boolean {
            val rawX = event.rawX.toInt()
            val rawY = event.rawY.toInt()

            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastX = rawX
                    lastY = rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    val offsetX = rawX - lastX
                    val offsetY = rawY - lastY
                    lastX = rawX
                    lastY = rawY
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