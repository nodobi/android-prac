package com.example.design_pattern_prac.presenter

import android.content.Context
import com.example.design_pattern_prac.data.ImageItem
import com.example.design_pattern_prac.data.ImageData

interface MainContract {
    interface View {
        fun updateItems(items: ArrayList<ImageItem>?, isClear: Boolean)
        fun notifyAdapter()
    }

    interface Presenter {
        var view: MainContract.View
        var imageData: ImageData

        fun loadItems(context: Context, isClear: Boolean)
    }
}