package com.example.design_pattern_prac.data

import android.content.Context

object ImageData {

    fun getSampleList(context: Context, size: Int) : ArrayList<ImageItem> {
        val list = ArrayList<ImageItem>()
        for (index in 0 .. size) {
            val name = String.format("sample_%02d", (Math.random() * 15).toInt())
            val resource = context.resources.getIdentifier(name, "drawable", context.packageName)
            list.add(ImageItem(resource, name))
        }
        return list
    }
}