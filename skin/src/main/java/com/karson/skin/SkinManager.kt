package com.karson.skin

import android.annotation.SuppressLint
import android.app.Application
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.drawable.Drawable
import java.io.File
import java.lang.Exception

object SkinManager {
    const val SKIN_NAME = "dark_skin.apk"
    private const val SKIN_PACKAGE_NAME = "com.karson.darkskin"

    private lateinit var app: Application
    private lateinit var skinFileName: String
    private lateinit var skinRes: Resources
    private var callback: Callback? = null

    interface Callback {
        fun onThemeUpdate()
    }

    fun setObserver(callback: Callback) {
        this.callback = callback
    }

    /**
     * 1.初始化Context
     * 2.拷贝皮肤包到指定目录（assets->cacheDir）
     * 3.反射AssetManager调用addAssetPath，加载皮肤包，生成皮肤Resources。
     * 4.通过宿主资源ID获取资源名、资源类别，皮肤Resources调用getIdentifier获取插件资源ID，获取对应的资源文件。
     */
    //1.初始化Context
    fun init(application: Application, skinFileName: String) {
        app = application
        this.skinFileName = skinFileName
        copySkinFile(skinFileName) { skinPath ->
            load(skinPath)
        }
    }

    //2.拷贝皮肤包到指定目录（assets->cacheDir）
    private fun copySkinFile(skinFileName: String, callback:(skinPath: String) -> Unit) {
        try {
            val inputStream = app.assets.open(skinFileName)
            val fileDir = app.cacheDir.absolutePath + File.separator + "skin"
            val file = File(fileDir, skinFileName)
            if (file.exists()) {
//                file.delete()
                callback(file.absolutePath)
                return
            }
            file.parentFile?.mkdirs()
            val outputStream = file.outputStream()
            var bytes: Int
            val buffer = ByteArray(1024)
            while (inputStream.read(buffer).also { bytes = it } != -1) {
                outputStream.write(buffer, 0, bytes)
            }
            callback(file.absolutePath)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //反射AssetManager调用addAssetPath，加载皮肤包，生成皮肤Resources。
    private fun load(skinPackagePath: String) {
        Thread {
            val assetManager = AssetManager::class.java.newInstance()
            val addAssetPath = assetManager.javaClass.getMethod("addAssetPath", String::class.java)
            addAssetPath.invoke(assetManager, skinPackagePath)
            //5.获取应用资源类
            val superRes = app.resources
            skinRes = Resources(assetManager, superRes.displayMetrics, superRes.configuration)
        }.start()
    }

    //4.通过宿主资源ID获取资源名、资源类别，皮肤Resources调用getIdentifier获取插件资源ID，获取对应的资源文件。
    @SuppressLint("UseCompatLoadingForDrawables")
    fun getDrawable(resId: Int): Drawable {
        val entryName = app.resources.getResourceEntryName(resId)
        val typeName = app.resources.getResourceTypeName(resId)
        val skinResId = skinRes.getIdentifier(entryName, typeName, SKIN_PACKAGE_NAME)
        return skinRes.getDrawable(skinResId, null)
    }
}