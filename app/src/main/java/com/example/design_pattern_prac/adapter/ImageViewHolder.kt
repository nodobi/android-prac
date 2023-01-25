package com.example.design_pattern_prac.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.design_pattern_prac.R

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageview by lazy {
        itemView.findViewById(R.id.img_view) as ImageView
    }

    val textView by lazy {
        itemView.findViewById(R.id.text) as TextView
    }
}