package com.example.mvp_fragment.view.note.adapter

interface NoteAdapterContract {
    interface Model {
        fun updateNoteList(noteList: ArrayList<String>)
    }
    interface View {
        fun notifyAdapter()
    }
}