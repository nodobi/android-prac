package com.example.design_pattern_prac.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.design_pattern_prac.R
import com.example.design_pattern_prac.adapter.contract.ImageAdapterContract
import com.example.design_pattern_prac.adapter.holder.ImageViewHolder
import com.example.design_pattern_prac.data.ImageItem
import java.lang.ref.WeakReference

class ImageAdapter(val context: Context) : RecyclerView.Adapter<ImageViewHolder>(),
    ImageAdapterContract.Model,
    ImageAdapterContract.View {

    var imageList: ArrayList<ImageItem>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false))
    }

    override fun getItemCount(): Int {
        return imageList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = imageList?.get(position)
        ImageAsync(holder.imageview).execute(item?.resource)
        holder.textView.text = item?.title

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Show ${item?.title}", Toast.LENGTH_SHORT).show()
        }

    }

    inner class ImageAsync(imageView: ImageView?) : AsyncTask<Int, Void, Bitmap>() {
        val imageViewReference: WeakReference<ImageView?>
        init {
            imageViewReference = WeakReference(imageView)
        }

        override fun doInBackground(vararg params: Int?): Bitmap {
            val options = BitmapFactory.Options()
            options.inSampleSize = 2
            return BitmapFactory.decodeResource(context.resources, params[0] as Int, options)
        }

        override fun onPreExecute() {
            super.onPreExecute()
            imageViewReference.get()?.setImageResource(0)
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            result?.let { imageViewReference.get()?.setImageBitmap(result)}
        }

    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun updateItems(items: ArrayList<ImageItem>?) {
        this.imageList = items
    }

    override fun clearItems() {
        imageList?.clear()
    }
}