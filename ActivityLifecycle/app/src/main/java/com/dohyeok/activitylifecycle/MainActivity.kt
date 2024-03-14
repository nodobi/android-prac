package com.dohyeok.activitylifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.dohyeok.activitylifecycle.databinding.ActivityMainBinding

class MainActivity : LifecycleActivity<ActivityMainBinding>(R.layout.activity_main) {
    override val name: String
        get() = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        databinding.btnToSecond.setOnClickListener {
            Log.i("Lifecycle", "$name| Change Activity")
            startActivity(
                Intent(this, SecondActivity::class.java)
            )
        }
    }
}