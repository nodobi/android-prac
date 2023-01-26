package com.example.design_pattern_prac.view.main.presenter

import android.content.Context
import com.example.design_pattern_prac.adapter.contract.ImageAdapterContract
import com.example.design_pattern_prac.data.ImageData
import com.example.design_pattern_prac.data.ImageItem
import com.example.design_pattern_prac.data.source.image.SampleImageRepository
import com.example.design_pattern_prac.data.source.image.SampleImageSource

class MainPresenter :
    MainContract.Presenter {

    override lateinit var view: MainContract.View
    override lateinit var sampleImageRepository: SampleImageRepository

    override lateinit var adapterModel: ImageAdapterContract.Model
    override var adapterView: ImageAdapterContract.View? = null
        set(value) {
            field = value
            field?.onClickFunc = { onClickListener(it) }
        }

    override fun loadItems(context: Context, isClear: Boolean) {
        sampleImageRepository.loadItems(context, 10, object: SampleImageSource.LoadImageCallback {
            override fun onLoadImage(list: ArrayList<ImageItem>) {
                if(isClear) {
                    adapterModel.clearItems()
                }
                adapterModel.updateItems(list)
                adapterView?.notifyAdapter()
            }
        })
    }

    private fun onClickListener(position: Int) {
        adapterModel.getItem(position).let {
            view.showToast(it.title)
        }
    }


}