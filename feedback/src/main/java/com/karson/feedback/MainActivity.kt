package com.karson.feedback

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.karson.feedback.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var commentAdapter: CommentAdapter
    private var mPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        commentAdapter = CommentAdapter()
        binding.rvComment.layoutManager = LinearLayoutManager(this)
        binding.rvComment.adapter = commentAdapter
        commentAdapter.setCallback(object : BaseCommonAdapter.Callback<CommentInfo> {
            override fun onItemClick(item: CommentInfo) {
                Toast.makeText(this@MainActivity, item.creator_name, Toast.LENGTH_SHORT).show()
//                Log.e(TAG, item.creator_name)
            }
        })

        binding.root.postDelayed({
            initData()
            initView()
        }, 1000)
    }

    private fun initView() {
        binding.refreshLayout.setOnRefreshListener {
            initData()
        }

        binding.refreshLayout.setOnLoadMoreListener {
            initData(++mPage)
        }
    }

    private fun initData(page: Int = 1) {
        val commentUrl = "https://m.fengshows.com/api/v3/hub/sns/comment/new/b77cf210-d954-11e9-b3eb-1f624e2a0b83/2?page=$page&page_size=50"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(commentUrl)
            .build()
        client.newCall(request).enqueue(object : Callback {
            @SuppressLint("NotifyDataSetChanged")
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    commentAdapter.setState(BaseCommonAdapter.State.ERROR)
                    commentAdapter.notifyDataSetChanged()
                    binding.refreshLayout.finishRefresh()
                    binding.refreshLayout.finishLoadMore()
                    mPage -= 1
                }
            }

            override fun onResponse(call: Call, response: Response) {
                binding.refreshLayout.finishRefresh()
                binding.refreshLayout.finishLoadMore()
                val json = response.body?.string()
                if (!TextUtils.isEmpty(json)) {
                    val gson = Gson()
                    val commentList = gson.fromJson<MutableList<CommentInfo>>(json, object : TypeToken<List<CommentInfo>>(){}.type)
                    val iterator = commentList.iterator()
                    while (iterator.hasNext()) {
                        val next = iterator.next()
                        if (next.fengshows_client.contains("ios")) {
                            iterator.remove()
                        }
                    }
                    initAdapter(commentList)
                }
            }

        })
    }

    private fun initAdapter(commentList: List<CommentInfo>) {
        runOnUiThread {
            commentAdapter.addData(commentList)
            commentAdapter.setState(BaseCommonAdapter.State.SUCCESS)
        }
    }
}