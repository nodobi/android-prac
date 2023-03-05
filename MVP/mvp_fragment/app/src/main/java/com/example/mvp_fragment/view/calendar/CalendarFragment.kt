package com.example.mvp_fragment.view.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_fragment.databinding.FragmentCalendarBinding
import com.example.mvp_fragment.view.base.BaseFragment
import com.example.mvp_fragment.view.calendar.contract.CalendarFragmentContract
import com.example.mvp_fragment.view.calendar.contract.CalendarFragmentPresenter
import com.example.mvp_fragment.view.calendar.adapter.CalendarAdapter
import java.time.LocalDate

class CalendarFragment(private val date: LocalDate) : BaseFragment<FragmentCalendarBinding>(),
    CalendarFragmentContract.View {
    private lateinit var mPresenter: CalendarFragmentPresenter
    private lateinit var mAdapter: CalendarAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCalendarBinding {
        return FragmentCalendarBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        mAdapter = CalendarAdapter(requireContext())
        mPresenter = CalendarFragmentPresenter().apply {
            view = this@CalendarFragment
            calendarAdapterModel = mAdapter
            calendarAdapterView = mAdapter
        }
        binding.recyclerviewCalendar.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 7)
            addItemDecoration(CalendarDecoration(requireContext()))
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        mPresenter.loadCalendarData(date)
    }
}