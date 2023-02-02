package com.karson.flow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.flow

/**
 * 数据源
 */
class MainViewModel : ViewModel() {

    /**
     * 冷流：没有任何接收端是不会工作的
     */
    val timeFlow = flow {
        var time = 0
        while (true) {
            emit(time) //发送数据到水管中
            kotlinx.coroutines.delay(1000)
            time++
        }
    }
}