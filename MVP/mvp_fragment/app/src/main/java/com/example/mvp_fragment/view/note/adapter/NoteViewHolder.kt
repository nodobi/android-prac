package com.example.mvp_fragment.view.note.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.databinding.ItemNoteBinding

class NoteViewHolder(val binding: ItemNoteBinding, val onItemClick: ((String) -> Unit)?) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(note: NoteItem) {
        binding.textviewNoteTitle.text = note.title
        binding.textviewNoteDetail.text = note.detail

        binding.root.setOnClickListener {
            onItemClick?.invoke(note.id)
        }
    }
}
