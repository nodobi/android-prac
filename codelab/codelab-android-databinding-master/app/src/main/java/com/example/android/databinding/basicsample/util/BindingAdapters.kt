/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.databinding.basicsample.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.Popularity

/**
 *  Sets the value of the progress bar so that 5 likes will fill it up.
 *
 *  Showcases Binding Adapters with multiple attributes. Note that this adapter is called
 *  whenever any of the attribute changes.
 */
@BindingAdapter(value = ["app:progressScaled", "android:max"], requireAll = true)
fun setProgress(progressBar: ProgressBar, likes: Int, max: Int) {
    progressBar.progress = (likes * max / 5).coerceAtMost(max)
}

/**
 * Unused Binding Adapter to replace the Binding Converter that hides a view if the number
 * of likes is zero.
 */
@BindingAdapter("app:hideIfZero")
fun hideIfZero(view: View, number: Int) {
    view.visibility = if (number == 0) View.GONE else View.VISIBLE
}


@BindingAdapter("app:popularity")
fun setPopularityIcon(view: ImageView, popularity: Popularity) {
    view.setColorFilter(getAssociatedColor(popularity, view.context))
    view.setImageDrawable(getDrawablePopularity(popularity, view.context))
}


private fun getAssociatedColor(popularity: Popularity, context: Context): Int {
    return when (popularity) {
        Popularity.NORMAL -> context.theme.obtainStyledAttributes(
            intArrayOf(android.R.attr.colorForeground)
        ).getColor(0, 0x000000)
        Popularity.POPULAR -> ContextCompat.getColor(context, R.color.popular)
        Popularity.STAR -> ContextCompat.getColor(context, R.color.star)
    }
}

private fun getDrawablePopularity(popularity: Popularity, context: Context): Drawable? {
    return when (popularity) {
        Popularity.NORMAL -> {
            ContextCompat.getDrawable(context, R.drawable.ic_person_black_96dp)
        }
        Popularity.POPULAR -> {
            ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp)
        }
        Popularity.STAR -> {
            ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp)
        }
    }
}
