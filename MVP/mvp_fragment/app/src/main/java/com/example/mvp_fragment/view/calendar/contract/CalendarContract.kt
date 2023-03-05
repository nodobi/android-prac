package com.example.mvp_fragment.view.calendar.contract

import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.view.base.BaseContract
import com.example.mvp_fragment.view.calendar.adapter.CalendarAdapterContract
import java.time.LocalDate

interface CalendarContract {
    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {
        var calendarAdapterView: CalendarAdapterContract.View
        var calendarAdapterModel: CalendarAdapterContract.Model
        var noteRepository: NoteRepository

        fun updateCalendarData(date: LocalDate)
    }
}