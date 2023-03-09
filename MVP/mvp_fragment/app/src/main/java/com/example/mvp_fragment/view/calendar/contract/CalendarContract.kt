package com.example.mvp_fragment.view.calendar.contract

import android.os.Bundle
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.view.base.BaseContract
import com.example.mvp_fragment.view.calendar.adapter.CalendarAdapterContract

interface CalendarContract {
    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {
        var calendarAdapterView: CalendarAdapterContract.View
        var calendarAdapterModel: CalendarAdapterContract.Model
        var noteRepository: NoteRepository

        fun updateCalendarData(arguments: Bundle?)
    }
}