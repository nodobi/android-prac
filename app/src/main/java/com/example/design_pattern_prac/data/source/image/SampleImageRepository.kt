package com.example.design_pattern_prac.data.source.image

import android.content.Context
import com.example.design_pattern_prac.data.ImageItem

class SampleImageRepository : SampleImageSource {

    private val sampleImageLocalDataSource: SampleImageLocalDataSource = SampleImageLocalDataSource

    override fun loadItems(
        context: Context,
        size: Int,
        loadImageCallback: SampleImageSource.LoadImageCallback?
    ) {
        sampleImageLocalDataSource.loadItems(context, size, object: SampleImageSource.LoadImageCallback {
            override fun onLoadImage(list: ArrayList<ImageItem>) {
                loadImageCallback?.onLoadImage(list)
            }
        })
    }
}