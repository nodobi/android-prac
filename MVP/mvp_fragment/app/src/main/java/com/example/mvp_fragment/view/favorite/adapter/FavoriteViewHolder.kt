package com.example.mvp_fragment.view.favorite.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.databinding.ItemNoteBinding

class FavoriteViewHolder(private val binding: ItemNoteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(noteItem: NoteItem) {
        binding.textviewNoteTitle.text = noteItem.title
        binding.textviewNoteDetail.text = noteItem.detail
        binding.imageViewNoteFavorite.isActivated = noteItem.isFavorite
    }
}