package com.karson.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import java.util.*

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

//    val stateFlow = timeFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

//    private var _timeFlow = MutableStateFlow(0)
//
//    val timeFlow = _timeFlow.asStateFlow()
//
//    fun startTime() {
//        val time = Timer()
//        time.scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                _timeFlow.value +=  1
//            }
//        }, 0, 1000)
//    }

}