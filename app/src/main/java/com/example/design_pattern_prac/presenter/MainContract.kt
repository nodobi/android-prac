package com.example.design_pattern_prac.presenter

import android.content.Context
import com.example.design_pattern_prac.adapter.contract.ImageAdapterContract
import com.example.design_pattern_prac.data.ImageData

interface MainContract {
    interface View {

    }

    interface Presenter {
        var view: MainContract.View
        var imageData: ImageData

        var adapterView: ImageAdapterContract.View
        var adapterModel: ImageAdapterContract.Model

        fun loadItems(context: Context, isClear: Boolean)
    }
}