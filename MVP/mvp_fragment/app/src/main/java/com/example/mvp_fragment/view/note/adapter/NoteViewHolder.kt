package com.example.mvp_fragment.view.note.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.databinding.ItemNoteBinding

class NoteViewHolder(
    val binding: ItemNoteBinding, val onItemClick: ((String) -> Unit)?,
    val onFavoriteClick: ((String, View) -> Unit)?
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(note: NoteItem) {
        binding.textviewNoteTitle.text = note.title
        binding.textviewNoteDetail.text = note.detail
        binding.imageViewNoteFavorite.isActivated = note.isFavorite

        binding.root.setOnClickListener {
            onItemClick?.invoke(note.id)
        }
        binding.imageViewNoteFavorite.setOnClickListener {
            onFavoriteClick?.invoke(note.id, it)
        }
    }
}
