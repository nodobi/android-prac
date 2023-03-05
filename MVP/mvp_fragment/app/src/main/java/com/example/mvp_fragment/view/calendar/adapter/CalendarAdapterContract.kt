package com.example.mvp_fragment.view.calendar.adapter

import com.example.mvp_fragment.data.NoteItem
import java.time.LocalDate

interface CalendarAdapterContract {
    interface View {
    }

    interface Model {
        fun updateDate(date: LocalDate)
        fun updateData(data: List<Pair<LocalDate, List<NoteItem>?>>)
        fun notifyAdapter()
    }
}