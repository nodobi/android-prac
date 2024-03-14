package com.dohyeok.activitylifecycle

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

abstract class LifecycleActivity<T : ViewBinding>(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {

    abstract val name: String

    lateinit var databinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        databinding = DataBindingUtil.setContentView(this, layoutRes)
        Log.i("Lifecycle", "$name| onCreate()")
    }

    override fun onStart() {
        super.onStart()
//        initView()

        Log.i("Lifecycle", "$name| onStart()")
    }

    override fun onResume() {
        super.onResume()

        Log.i("Lifecycle", "$name| onResume()")
    }

    override fun onPause() {
        super.onPause()

        Log.i("Lifecycle", "$name| onPause()")
    }

    override fun onStop() {
        super.onStop()

        Log.i("Lifecycle", "$name| onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("Lifecycle", "$name| onDestroy()")
    }
}