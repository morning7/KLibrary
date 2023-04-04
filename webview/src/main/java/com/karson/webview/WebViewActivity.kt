package com.karson.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.karson.webview.databinding.ActivityWebviewBinding

abstract class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding

    companion object {
        private const val TAG = "WebViewActivity"
        private const val ANDROID = "android"
        private const val FILE_ASSET = "file:///android_asset/"
        private const val FILE_CAPTCHA = FILE_ASSET + "captcha.html"
        private const val FILE_TEST = FILE_ASSET + "test.html"
        private const val URL = "https://www.baidu.com"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWebView()
        initListener()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        initWebViewClient()
        initWebViewChromeClient()
        val setting = binding.webView.settings
        setting.javaScriptEnabled = true
        androidCallJs()
        jsCallAndroid()
//        loadUrl(FILE_TEST)
        binding.webView.loadUrl(getUrl())
    }

    private fun initListener() {
        binding.refresh.setOnRefreshListener {
            binding.refresh.finishRefresh()
            binding.webView.loadUrl(getUrl())
        }
    }

    abstract fun getUrl(): String

    private fun jsCallAndroid() {
        /**
         * 方法一
         * 类(AndroidJs):方法集合
         * 别名(android):对象映射
         *
         * 优点：使用简单，仅将Android对象和JS对象映射即可
         * 缺点：存在严重的漏洞问题，漏洞产生原因是：当JS拿到Android这个对象后，就可以调用这个Android对象中所有的方法，
         * 包括系统类（java.lang.Runtime 类），从而进行任意代码执行。如可以执行命令获取本地设备的SD卡中的文件等信息从而造成信息泄露。
         * Google 在Android 4.2 版本中规定对被调用的函数以@JavascriptInterface进行注解从而避免漏洞攻击方式
         */
        binding.webView.addJavascriptInterface(object : AndroidJs {

            @JavascriptInterface
            override fun toast(text: String) {
                showToast(text)
            }

            @JavascriptInterface
            override fun log(text: String) {
                Log.e(TAG, text)
            }

            @JavascriptInterface
            override fun alert(text: String) {
                AlertDialog.Builder(this@WebViewActivity)
                    .setTitle(text)
                    .setMessage(text)
                    .setPositiveButton("Confirm") { _, _ ->  }
                    .setNegativeButton("Cancel") { _, _ ->  }
                    .setCancelable(false)
                    .show()
            }

            @JavascriptInterface
            fun getAndroidResult(): String {
                return "android"
            }
        }, ANDROID)

        /**
         * 方法二
         * 通过 WebViewClient 的shouldOverrideUrlLoading ()方法回调拦截url/request
         * 解析Uri 校验scheme、authority，获取参数值
         *
         * 优点：不存在漏洞
         * 缺点：1.协议校验
         *      2.JS获取Android方法的返回值复杂
         */

        /**
         * 方法三
         * 优点：不存在漏洞
         *
         * 通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt（）方法回调拦截JS对话框alert()、confirm()、prompt()
         * 获取message
         */
    }

    private fun androidCallJs() {
        binding.btn.setOnClickListener {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                /**
                 * 无返回值
                */
                binding.webView.loadUrl("javascript:sum(3,4)")

                /**
                 * 处理返回值
                 * 1.调用js方法，嵌套alert、confirm、prompt使用，ChromeClient中获取返回值
                 * 2.调用js方法，嵌套android.toast使用，JavascriptInterface中获取返回值
                 * 3.调用js方法，js方法里调用android方法获取返回值
                 */
                binding.webView.loadUrl("javascript:alert(sum(4,4))")
                binding.webView.loadUrl("javascript:android.toast(sum(4,5))")

                /**
                 * android写js(html不用写)
                 * 格式：javascript:(function(){...})()
                 */
                binding.webView.loadUrl("javascript:(function(){android.toast('test')})()")
            } else {
                /**
                 * 1.API-19（4.4）以上
                 * 2.效率高（相比loadUrl不需要刷新）
                 * 3.容易处理返回值
                 * 4.不需要javascript前缀
                 */
//                binding.webView.evaluateJavascript("alert(sum(3,4))") {}
                binding.webView.evaluateJavascript("android.alert(sum(3,4))") {}
//                binding.webView.evaluateJavascript("(function(){android.toast('test')})()") {}

//                binding.webView.evaluateJavascript("sum(3,4)") {
//                    showToast(it)
//                }
            }
        }
    }

    private fun initWebViewClient() {
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                /**
                 * 调用js方法需要加载完毕
                 * android调用JS方法获取HTML标签等源码
                 */
                view.loadUrl("javascript:window.android.log(document.getElementsByTagName('html')[0].innerHTML);")
            }

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                val uri = request.url
                if ("js" == uri.scheme && "webview" == uri.authority) {
                    val arg1 = uri.getQueryParameter("arg1")
                    val arg2 = uri.getQueryParameter("arg2")
                    Log.e(TAG, "arg1 $arg1 arg2 $arg2")
                    return true
                }
                return super.shouldOverrideUrlLoading(view, request)
            }

            /**
             * 兼容低版本
             */
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
    }

    private fun initWebViewChromeClient() {
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                binding.progress.progress = newProgress
                if (newProgress == 100) {
                    binding.progress.visibility = View.GONE
                }
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                setTitle(title)
            }

            /**
             * Webview加载html中有alert()执行的时候，会回调这个方法
             * url:当前Webview显示的url
             * message：alert的参数值
             * JsResult：java将结果回传到js中
             */
            override fun onJsAlert(
                view: WebView,
                url: String,
                message: String,
                result: JsResult
            ): Boolean {
                showToast(message)
                result.confirm()
                return true
//                return super.onJsAlert(view, url, message, result)
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this@WebViewActivity, text, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    interface AndroidJs {
        fun toast(text: String)

        fun log(text: String)

        fun alert(text: String)
    }
}