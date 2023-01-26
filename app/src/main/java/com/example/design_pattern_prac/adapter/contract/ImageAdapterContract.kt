package com.example.design_pattern_prac.adapter.contract

import com.example.design_pattern_prac.data.ImageItem

interface ImageAdapterContract {
    interface View {
        var onClickFunc: ((Int) -> Unit)?
        fun notifyAdapter()
    }

    interface Model {
        fun updateItems(items: ArrayList<ImageItem>)

        fun clearItems()
        fun getItem(position: Int): ImageItem
    }
}