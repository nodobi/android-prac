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
    private lateinit var mPresenter: HomePresenter
    private lateinit var mAdapter: CalendarPagerAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        mAdapter = CalendarPagerAdapter(requireActivity())
        mPresenter = HomePresenter().apply {
            view = this@HomeFragment
            calendarPagerView = mAdapter
            calendarPagerModel = mAdapter
        }

        binding.viewpagerHome.apply {
            adapter = mAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            registerOnPageChangeCallback(
                object : OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        mPresenter.changeDisplayDate(position)
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