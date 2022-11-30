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
import com.karson.webview.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {
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
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        initWebViewClient()
        initWebViewChromeClient()
        val setting = binding.webView.settings
        setting.javaScriptEnabled = true
        androidCallJs()
        jsCallAndroid()
        binding.webView.loadUrl(FILE_TEST)
    }

    private fun jsCallAndroid() {
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
            }

        }, ANDROID)
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
//                binding.webView.evaluateJavascript("android.toast(sum(3,4))") {}
//                binding.webView.evaluateJavascript("(function(){android.toast('test')})()") {}

                binding.webView.evaluateJavascript("sum(3,4)") {
                    showToast(it)
                }
            }
        }
    }

    private fun initWebViewClient() {
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                //调用js方法需要加载完毕
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

            override fun onJsAlert(
                view: WebView,
                url: String,
                message: String,
                result: JsResult
            ): Boolean {
//                showToast(message)
//                result.confirm()
//                return true
                return super.onJsAlert(view, url, message, result)
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