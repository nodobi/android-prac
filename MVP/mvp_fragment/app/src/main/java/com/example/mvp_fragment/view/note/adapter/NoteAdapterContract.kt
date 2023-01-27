package com.example.mvp_fragment.view.note.adapter

import com.example.mvp_fragment.data.NoteItem

interface NoteAdapterContract {
    interface Model {
        fun updateNoteList(noteList: ArrayList<NoteItem>)
    }
    interface View {
        fun notifyAdapter()
    }
}