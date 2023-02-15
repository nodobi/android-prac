package com.example.mvp_fragment.view.home.presenter

import com.example.mvp_fragment.view.base.BaseContract
import com.example.mvp_fragment.view.home.adapter.CalendarPagerAdapterContract

interface HomeContract : BaseContract {
    interface View : BaseContract.View {
        fun changeDisplayDate(year: String, month: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        var calendarPagerView: CalendarPagerAdapterContract.View
        var calendarPagerModel: CalendarPagerAdapterContract.Model?
    }

}