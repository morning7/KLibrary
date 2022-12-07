package com.karson.klibrary.rxlogin

import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.reactivex.subjects.PublishSubject

class LoginSubjectFragment : Fragment() {

    private val subject: PublishSubject<Boolean> = PublishSubject.create()

    private val result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        subject.onNext(it.resultCode == Activity.RESULT_OK)
    }

    fun create(activity: AppCompatActivity): LoginSubjectFragment {
        activity.supportFragmentManager.beginTransaction().add(this, "login").commitNow()
        return this
    }

    fun login(): PublishSubject<Boolean> {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        result.launch(intent)
//        startActivityForResult(intent, 0)
        return subject
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        subject.onNext(resultCode == Activity.RESULT_OK)
//    }
}

