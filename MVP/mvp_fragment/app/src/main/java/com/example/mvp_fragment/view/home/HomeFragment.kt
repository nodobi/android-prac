package com.example.mvp_fragment.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvp_fragment.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.inflate(layoutInflater, null, false)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}