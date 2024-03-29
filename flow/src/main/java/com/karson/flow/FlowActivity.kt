package com.karson.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.karson.flow.databinding.ActivityFlowBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
/**
 *  水源（数据源：网络获取或者本地数据库）
 *  +
 *  水管 （数据处理：响应式编程flow、Rxjava）
 *  +
 *  水龙头（数据展示：用户交互，客户端展示内容）
 */
class FlowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlowBinding
    private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn.setOnClickListener {
            //launchWhenStarted函数就是用于保证只有在Activity处于Started状态的情况下
//            lifecycleScope.launchWhenStarted {
//                //collect：相当于把水龙头接到水管上并打开 Observable.subscribe
//                mainViewModel.timeFlow.collect { time ->
//                    binding.tvTime.text = time.toString()
//                    Log.e("FlowTest", "Update time $time in UI.")
//                }

//            lifecycleScope.launch {
//                repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    mainViewModel.timeFlow.collect { time ->
//                        binding.tvTime.text = time.toString()
//                        Log.e("FlowTest", "Update time $time in UI.")
//                    }
//                }
//            }

//                mainViewModel.timeFlow.collectLatest { time ->
//                    binding.tvTime.text = time.toString()
//                    delay(3000)
//                }

//            mainViewModel.startTime()
        }

        lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    mainViewModel.timeFlow.collect { time ->
                        binding.tvTime.text = time.toString()
                        Log.e("FlowTest", "Update time $time in UI.")
                    }
                }
            }
    }
}