package com.example.mvp_fragment.view.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CalendarPagerAdapter(
    fragmentActivity: FragmentActivity
) :
    FragmentStateAdapter(fragmentActivity), CalendarPagerAdapterContract.View,
    CalendarPagerAdapterContract.Model {
    private var pageCount: Int = 10

    override var onPagerCreateFragment: ((Int) -> Fragment)? = null

    override fun getItemCount(): Int {
        return pageCount
    }

    override fun createFragment(position: Int): Fragment {
        return onPagerCreateFragment!!.invoke(position)
    }

    override fun setPageCount(count: Int) {
        pageCount = count
    }

}