package com.karson.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


fun getToken(): Flow<String> = flow {
    emit("token")
}

fun getUserInfo(token: String) = flow {
    emit("userinfo")
}

fun main() {
    runBlocking {
        /**
         * 背压三剑客
         * buffer、collectLatest、conflate
         * 解决flow流速不均匀的问题
         */

        flow {
            var count = 0
            while (true) {
                emit(count)
                delay(1000)
                count ++
            }
        }.conflate()
            .collect {
                println("start task $it")
                delay(2000)
                println("finish task $it")
        }

//        flow {
//            emit(1)
//            delay(1000)
//            emit(2)
//            delay(1000)
//            emit(3)
//        }.onEach {
//            println("$it is ready")
//        }.buffer() //让flow函数和collect函数运行在不同的协程当中
//            .collect {
//            delay(1000)
//            println("$it is handled")
//        }


//        /**
//         * 操作符zip
//         */
//        val flow1 = flowOf(1, 2, 3, 4, 5)
//        val flow2 = flowOf('a', 'b', 'c')
//        flow1.zip(flow2) { a, b ->
//            a.toString() + b
//        }.collect {
//            println(it.toString())
//        }

//        /**
//         * 操作符
//         * flatmapConcat:连接，保证顺序
//         * flatmapMerge:合并、不保证顺序
//         * flatmapLatest:只接收处理最新的数据，上一个未处理完的逻辑取消
//         */
//        getToken()
//        .flatMapConcat {
//           getUserInfo(it)
//        }
//        .flowOn(Dispatchers.IO)
//        .collect {
//            println(it)
//        }

//        flowOf(1, 2, 3)
//            .flatMapConcat {
//                flowOf("a$it", "b$it")
//            }
//            .collect {
//                println(it)
//            }

//        /**
//         * 终端操作符函数
//         */
//        val result = flow {
//            for (i in (1..100)) {
//                emit(i)
//            }
//        }.reduce { /* 累积值 */ accumulator, value ->
//            accumulator + value
//        }
//        println(result)

//        val result = flow {
//            for (i in ('A'..'Z')) {
//                emit(i)
//            }
//        }.fold("Alphabet: ") { /* 累积值 */ accumulator, value ->
//            accumulator + value
//        }
//        println(result)

//        val result = flow {
//            for (i in (1..100)) {
//                emit(i)
//            }
//        }.fold(1) { /* 累积值 */ accumulator, value ->
//            accumulator + value
//        }
//        println(result)

//        /**
//         * 操作符sample
//         */
//        flow {
//            while (true) {
//                emit("发送一条弹幕")
//            }
//        }.sample(1000)
//            .flowOn(Dispatchers.IO)
//            .collect {
//                println(it)
//        }

//        /**
//         * 操作符 debounce
//         */
//
//        flow {
//            emit(1)
//            emit(2)
//            kotlinx.coroutines.delay(600)
//            emit(3)
//            kotlinx.coroutines.delay(100)
//            emit(4)
//            kotlinx.coroutines.delay(100)
//            emit(5)
//        }.debounce(500)
//            .collect {
//                println(it)
//            }


//        /**
//         * 操作符map、filter、onEach
//         */
//        flowOf(1, 2, 3, 4, 5)
//            .onEach {
//                println(it.toString())
//            }
//            .filter {
//                it % 2 == 0
//            }
//            .map {
//                it * 2
//            }
//            .collect {
//                println(it.toString())
//            }
    }
}