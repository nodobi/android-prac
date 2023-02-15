package com.example.mvp_fragment.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.mvp_fragment.databinding.FragmentHomeBinding
import com.example.mvp_fragment.view.base.BaseFragment
import com.example.mvp_fragment.view.home.adapter.CalendarPagerAdapter
import com.example.mvp_fragment.view.home.presenter.HomeContract
import com.example.mvp_fragment.view.home.presenter.HomePresenter

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeContract.View {
    private lateinit var homePresenter: HomePresenter
    private lateinit var pagerAdapter: CalendarPagerAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        pagerAdapter = CalendarPagerAdapter(requireActivity())

        homePresenter = HomePresenter().apply {
            view = this@HomeFragment
            calendarPagerView = pagerAdapter
            calendarPagerModel = pagerAdapter
        }

        binding.viewpagerHome.apply {
            adapter = pagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            registerOnPageChangeCallback(
                object : OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        homePresenter.changeDisplayDate(position)
                    }
                }
            )
        }

    }

    override fun changeDisplayDate(year: String, month: String) {
        binding.textviewHomeYear.text = year
        binding.textviewHomeMonth.text = month
    }

}