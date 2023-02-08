package com.example.mvp_fragment.view.favorite.adapter

import com.example.mvp_fragment.data.NoteItem

interface FavoriteAdapterContract {
    interface Model {
        fun updateNoteList(noteList: List<NoteItem>)
    }

    interface View {
        fun notifyAdapter()
    }
}