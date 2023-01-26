package com.example.design_pattern_prac.data.source.image

import android.content.Context
import com.example.design_pattern_prac.data.ImageItem

interface SampleImageSource {
    interface LoadImageCallback {
        fun onLoadImage(list : ArrayList<ImageItem>)
    }

    fun loadItems(context: Context, size: Int, loadImageCallback: LoadImageCallback?)
}