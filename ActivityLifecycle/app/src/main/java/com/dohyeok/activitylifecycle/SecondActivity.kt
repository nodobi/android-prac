package com.dohyeok.activitylifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.dohyeok.activitylifecycle.databinding.ActivitySecondBinding

class SecondActivity : LifecycleActivity<ActivitySecondBinding>(R.layout.activity_second) {
    override val name: String
        get() = "SecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        databinding.btnToMain.setOnClickListener {
            Log.i("Lifecycle", "$name| Change Activity")
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }
}