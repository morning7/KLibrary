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
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
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
            function()
        }
    }

    /**
     * 功能操作符
     */
    private fun function() {
        //delay

        /**
         * 周期函数
         */
        Observable.just(1, 2, 3)
            .doOnNext {
                print("doOnNext")
            }
            .doFinally {
                print("doFinally")
            }
            .subscribe {
                print(it.toString())
            }.addTo(composite)

        //subscribeOn(): 指定被观察者的线程，如果多次调用此方法，只有第一次有效。
        //observeOn(): 指定观察者的线程
    }

    /**
     * 组合操作符
     */
    private fun combine() {
        /**
         * zip操作符用于将多个数据源合并，并生成一个新的数据源。
         * 新生成的数据源严格按照合并前的数据源的数据发射顺序，
         * 并且新数据源的数据个数等于合并前发射数据个数最少的那个数据源的数据个数。
         */
        val intObservable = Observable.just(1, 2, 3)
        val stringObservable = Observable.just("A", "B")
        Observable.zip(intObservable, stringObservable,
            { t1, t2 ->
                t1.toString() + t2
            }).subscribe { t ->
                print(t)
        }.addTo(composite)

//        Observable.concat(Observable.just(1), Observable.just("2")).subscribe {
//            print(it.toString())
//        }.addTo(composite)

//        intObservable.count().subscribe { count ->
//            print(count.toString())
//        }.addTo(composite)
    }


    /**
     * 转换操作符
     */
    private fun transform() {
        //map 转换数据
        //flatmap 转换被观察者（并行）
        //contactMap 转换被观察者（顺序）
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