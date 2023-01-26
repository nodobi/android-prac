package com.example.mvp_fragment.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvp_fragment.databinding.ActivityMainBinding
import com.example.mvp_fragment.view.main.presenter.MainContract

class MainActivity :
    AppCompatActivity(),
    MainContract.View
{
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}