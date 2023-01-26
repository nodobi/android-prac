package com.example.design_pattern_prac.adapter.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.design_pattern_prac.R
import com.example.design_pattern_prac.data.ImageItem
import com.example.design_pattern_prac.util.ImageAsync

class ImageViewHolder(itemView: View, var onClickFunc: ((Int) -> Unit)?) : RecyclerView.ViewHolder(itemView) {

    val imageView by lazy {
        itemView.findViewById(R.id.img_view) as ImageView
    }

    val textView by lazy {
        itemView.findViewById(R.id.text) as TextView
    }

    fun onBind(item: ImageItem, position: Int) {
        ImageAsync(imageView.context, imageView).execute(item.resource)
        textView.text = item.title

        itemView.setOnClickListener {
            onClickFunc?.invoke(position)
        }
    }
}