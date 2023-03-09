package com.example.mvp_fragment.view.home.adapter

import androidx.fragment.app.Fragment

interface CalendarPagerAdapterContract {
    interface View {

    }

    interface Model {
        var onPagerCreateFragment: ((Int) -> Fragment)?

        fun setPageCount(count: Int)
    }
}