package com.example.mvp_fragment.view.favorite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.databinding.ItemNoteBinding

class FavoriteAdapter(private val context: Context) : RecyclerView.Adapter<FavoriteViewHolder>(),
    FavoriteAdapterContract.View, FavoriteAdapterContract.Model {
    private var favoriteNoteList: List<NoteItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return favoriteNoteList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.onBind(favoriteNoteList[position])
    }

    override fun updateNoteList(noteList: List<NoteItem>) {
        favoriteNoteList = noteList
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }
}