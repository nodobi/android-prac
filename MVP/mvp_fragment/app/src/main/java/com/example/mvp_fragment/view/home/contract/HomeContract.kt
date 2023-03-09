package com.example.mvp_fragment.view.home.contract

import com.example.mvp_fragment.view.base.BaseContract
import com.example.mvp_fragment.view.home.adapter.CalendarPagerAdapterContract

interface HomeContract : BaseContract {
    interface View : BaseContract.View {
        fun changeDisplayDate(year: String, month: String)
        fun setPagePosition(position: Int)
    }

    interface Presenter : BaseContract.Presenter<View> {
        var calendarPagerView: CalendarPagerAdapterContract.View
        var calendarPagerModel: CalendarPagerAdapterContract.Model?
        var onPageSelected: (Int) -> Unit

        fun initPageCount()
    }

}