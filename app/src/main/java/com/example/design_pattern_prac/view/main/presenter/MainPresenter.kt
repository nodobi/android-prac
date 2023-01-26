package com.example.design_pattern_prac.view.main.presenter

import android.content.Context
import com.example.design_pattern_prac.adapter.contract.ImageAdapterContract
import com.example.design_pattern_prac.data.ImageData

class MainPresenter :
    MainContract.Presenter {

    override lateinit var view: MainContract.View
    override lateinit var imageData: ImageData

    override lateinit var adapterModel: ImageAdapterContract.Model
    override var adapterView: ImageAdapterContract.View? = null
        set(value) {
            field = value
            field?.onClickFunc = { onClickListener(it) }
        }

    override fun loadItems(context: Context, isClear: Boolean) {
        if(isClear) {
            adapterModel.clearItems()
        }
        adapterModel.updateItems(imageData.getSampleList(context, 10))
        adapterView?.notifyAdapter()
    }

    private fun onClickListener(position: Int) {
        adapterModel.getItem(position).let {
            view.showToast(it.title)
        }
    }


}