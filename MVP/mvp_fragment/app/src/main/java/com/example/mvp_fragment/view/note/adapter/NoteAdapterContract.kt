package com.example.mvp_fragment.view.note.adapter

import com.example.mvp_fragment.data.NoteItem

interface NoteAdapterContract {
    interface Model {
        fun updateNoteList(noteList: List<NoteItem>)
    }
    interface View {
        var onItemClick: ((String) -> Unit)?
        var onFavoriteClick: ((String, android.view.View) -> Unit)?

        fun notifyAdapter()
    }
}