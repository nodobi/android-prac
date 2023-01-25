package com.example.design_pattern_prac.presenter

import android.content.Context
import com.example.design_pattern_prac.adapter.contract.ImageAdapterContract
import com.example.design_pattern_prac.data.ImageData

class MainPresenter :
    MainContract.Presenter {

    override lateinit var view: MainContract.View
    override lateinit var imageData: ImageData

    override lateinit var adapterView: ImageAdapterContract.View
    override lateinit var adapterModel: ImageAdapterContract.Model

    override fun loadItems(context: Context, isClear: Boolean) {
        if(isClear) {
            adapterModel.clearItems()
        }
        adapterModel.updateItems(imageData.getSampleList(context, 10))
        adapterView.notifyAdapter()
    }



}