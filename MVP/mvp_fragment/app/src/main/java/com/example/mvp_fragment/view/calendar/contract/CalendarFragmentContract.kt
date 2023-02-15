package com.example.mvp_fragment.view.calendar.contract

import com.example.mvp_fragment.view.base.BaseContract
import com.example.mvp_fragment.view.calendar.adapter.CalendarAdapterContract
import java.time.LocalDate

interface CalendarFragmentContract {
    interface View: BaseContract.View {

    }
    interface Presenter: BaseContract.Presenter<View> {
        var calendarAdapterView: CalendarAdapterContract.View
        var calendarAdapterModel: CalendarAdapterContract.Model

        fun loadCalendarData(date: LocalDate)
    }
}