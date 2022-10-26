package com.karson.language

import android.content.Context
import android.util.Log
import androidx.annotation.AnyRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

object LanguageManager {

    private const val TAG = "LanguageManager"
    private const val URL = "https://m.fengshows.com/api/v3/ui_language/fengshows_mobile/languages/zh-HK"
    private var languageMap: Map<String, String>? = null
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
        requestLanguage()
    }

    private fun requestLanguage() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(URL)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "onFailure ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body!!.string()
                Log.e(TAG, json)
                languageMap = Gson().fromJson(json, object : TypeToken<Map<String, String>>(){}.type)
            }
        })
    }

    fun getString(@AnyRes resId: Int): String {
        val entryName = appContext.resources.getResourceEntryName(resId)
        return if (entryName != null && languageMap != null && languageMap!!.contains(entryName) && languageMap!![entryName]!!.isNotEmpty()) {
            languageMap!![entryName]!!
        } else {
            appContext.resources.getString(resId)
        }
    }
}