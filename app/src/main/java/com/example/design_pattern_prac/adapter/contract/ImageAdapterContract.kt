package com.example.design_pattern_prac.adapter.contract

import com.example.design_pattern_prac.data.ImageItem
import com.example.design_pattern_prac.presenter.MainPresenter

interface ImageAdapterContract {
    interface View {
        fun notifyAdapter()
    }

    interface Model {
        fun updateItems(items: ArrayList<ImageItem>?)

        fun clearItems()
    }
}