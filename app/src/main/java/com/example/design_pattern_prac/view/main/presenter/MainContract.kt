package com.example.design_pattern_prac.view.main.presenter

import android.content.Context
import com.example.design_pattern_prac.adapter.contract.ImageAdapterContract
import com.example.design_pattern_prac.data.ImageData

interface MainContract {
    interface View {
        fun showToast(title: String)

    }

    interface Presenter {
        var view: View
        var imageData: ImageData

        var adapterView: ImageAdapterContract.View?
        var adapterModel: ImageAdapterContract.Model

        fun loadItems(context: Context, isClear: Boolean)
    }
}