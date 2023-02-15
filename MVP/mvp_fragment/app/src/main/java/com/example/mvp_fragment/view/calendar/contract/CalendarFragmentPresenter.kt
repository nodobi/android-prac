package com.example.mvp_fragment.view.calendar.contract

import com.example.mvp_fragment.view.calendar.adapter.CalendarAdapterContract
import java.time.LocalDate

class CalendarFragmentPresenter: CalendarFragmentContract.Presenter {
    override lateinit var view: CalendarFragmentContract.View
    override lateinit var calendarAdapterView: CalendarAdapterContract.View
    override lateinit var calendarAdapterModel: CalendarAdapterContract.Model

    override fun loadCalendarData(date: LocalDate) {
        calendarAdapterModel.updateDateList(date)
    }
}