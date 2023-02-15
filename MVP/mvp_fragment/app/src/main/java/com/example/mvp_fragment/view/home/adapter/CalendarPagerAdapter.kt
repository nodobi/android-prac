package com.example.mvp_fragment.view.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CalendarPagerAdapter(
    fragmentActivity: FragmentActivity
) :
    FragmentStateAdapter(fragmentActivity), CalendarPagerAdapterContract.View,
    CalendarPagerAdapterContract.Model {

    override var onPagerCreateFragment: ((Int) -> Fragment)? = null

    override fun getItemCount(): Int {
        return 10
    }

    override fun createFragment(position: Int): Fragment {
        return onPagerCreateFragment!!.invoke(position)
    }

}