package com.karson.device

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.github.gzuliyujiang.oaid.DeviceIdentifier
import com.github.gzuliyujiang.oaid.IGetter
import com.github.gzuliyujiang.oaid.DeviceID
import com.karson.device.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDeviceInfo()
    }

    private fun getDeviceInfo() {
        val guid = DeviceIdentifier.getGUID(this)
        if (!TextUtils.isEmpty(guid)) {
            showDeviceInfo(guid)
        }
        // 是否支持OAID/AAID
        DeviceID.supportedOAID(this)
        // 获取OAID/AAID，同步调用
        DeviceIdentifier.getOAID(this)
        // 获取OAID/AAID，异步回调
        DeviceID.getOAID(this, object : IGetter {
            override fun onOAIDGetComplete(result: String) {
                // 不同厂商的OAID/AAID格式是不一样的，可进行MD5、SHA1之类的哈希运算统一
//                AppLogUtils.INSTANCE.e("===oaid $result")
                showDeviceInfo(result)
            }

            override fun onOAIDGetError(error: Exception) {
            }
        })
    }

    private fun showDeviceInfo(result: String) {
        binding.tvDevice.text = result
        copyText(result)
    }

    private fun copyText(text: String) {
        val cmb: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        cmb.setPrimaryClip(ClipData.newPlainText(null, text))
        Toast.makeText(this, "已复制到剪贴板", Toast.LENGTH_SHORT).show()
    }
}