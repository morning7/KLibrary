package com.karson.webview

/**
 * 乌有之乡
 */
class WyActivity : WebViewActivity() {
    companion object {
        private const val URL_WY = "http://m.wyzxwk.com"
    }

    override fun getUrl() = URL_WY
}