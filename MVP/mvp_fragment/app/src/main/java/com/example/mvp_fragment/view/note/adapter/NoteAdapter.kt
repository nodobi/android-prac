package com.example.mvp_fragment.view.note.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.databinding.ItemNoteBinding

class NoteAdapter(val context: Context) : RecyclerView.Adapter<NoteViewHolder>(),
    NoteAdapterContract.View, NoteAdapterContract.Model {
    var noteList: List<NoteItem> = listOf()
    override var onItemClick: ((String) -> Unit)? = null
    override var onFavoriteClick: ((String, View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(context), parent, false), onItemClick, onFavoriteClick)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(noteList[position])
    }

    override fun updateNoteList(noteList: List<NoteItem>) {
        this.noteList = noteList
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }
}