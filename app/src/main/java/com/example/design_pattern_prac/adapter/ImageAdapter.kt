package com.example.design_pattern_prac.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.design_pattern_prac.R
import com.example.design_pattern_prac.adapter.contract.ImageAdapterContract
import com.example.design_pattern_prac.adapter.holder.ImageViewHolder
import com.example.design_pattern_prac.data.ImageItem

class ImageAdapter(
    val context: Context
) :
    RecyclerView.Adapter<ImageViewHolder>(),
    ImageAdapterContract.Model,
    ImageAdapterContract.View
{
    private lateinit var imageList: ArrayList<ImageItem>

    override var onClickFunc: ((Int) -> Unit)? = null

    override fun getItem(position: Int): ImageItem {
        return imageList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false), onClickFunc)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        imageList[position].let {
            holder.onBind(it, position)
        }
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun updateItems(items: ArrayList<ImageItem>) {
        this.imageList = items
    }

    override fun clearItems() {
        imageList.clear()
    }
}