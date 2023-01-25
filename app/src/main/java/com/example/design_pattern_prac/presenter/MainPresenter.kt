package com.example.design_pattern_prac.presenter

import android.content.Context
import com.example.design_pattern_prac.data.ImageData

class MainPresenter : MainContract.Presenter {

    lateinit override var view: MainContract.View
    lateinit override var imageData: ImageData

    override fun loadItems(context: Context, isClear: Boolean) {
        imageData.getSampleList(context, 10).let {
            view.updateItems(it, isClear)
            view.notifyAdapter()
        }
    }
}