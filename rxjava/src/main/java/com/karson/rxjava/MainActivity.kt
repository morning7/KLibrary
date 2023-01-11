package com.karson.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.karson.rxjava.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tag = "rxjava--->"

    private val composite = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            create()
        }
    }

    /**
     * 创建操作符
     */
    private fun create() {
        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                print("订阅")
            }

            override fun onNext(t: String) {
                print(t)
            }

            override fun onError(e: Throwable) {
                print("错误")
            }

            override fun onComplete() {
                print("完成")
            }
        }

//        //create
//        Observable.create(ObservableOnSubscribe<String> {
//            it.onNext("中国")
//            it.onNext("加油")
//            it.onComplete()
//        }).subscribe(observer)

//        //just
//        Observable.just("1", "2", "3").subscribe(observer)

//        //time 延迟操作
//        Observable.timer(500, TimeUnit.MILLISECONDS).subscribe {
//            print(it.toString())
//        }.addTo(composite)

//        //interval 轮询
//        Observable.interval(500, TimeUnit.MILLISECONDS).subscribe {
//            print(it.toString())
//        }.addTo(composite)

//        Observable.intervalRange(10, 5, 500, 500, TimeUnit.MILLISECONDS).subscribe {
//            print(it.toString())
//        }.addTo(composite)

//        //rang
//        Observable.range(10, 5).subscribe {
//            print(it.toString())
//        }.addTo(composite)

//        //empty,never,error
//        Observable.empty<String>().subscribe(observer)
//        Observable.never<String>().subscribe(observer)
//        Observable.error(Throwable("error"))
    }

    private fun print(string: String) {
        Log.e(tag, string)
    }
}