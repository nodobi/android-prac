package com.example.mvp_fragment.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvp_fragment.databinding.FragmentHomeBinding
import com.example.mvp_fragment.view.base.BaseFragment

class HomeFragment: BaseFragment<FragmentHomeBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initViews() {

    }
}