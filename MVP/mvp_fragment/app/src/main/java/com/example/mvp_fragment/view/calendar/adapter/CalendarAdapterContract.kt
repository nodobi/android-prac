package com.example.mvp_fragment.view.calendar.adapter

import java.time.LocalDate

interface CalendarAdapterContract {
    interface View {
    }

    interface Model {
        fun updateDateList(date: LocalDate)

    }
}