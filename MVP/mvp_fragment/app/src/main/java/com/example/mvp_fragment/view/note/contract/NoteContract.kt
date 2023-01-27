package com.example.mvp_fragment.view.note.contract

import com.example.mvp_fragment.view.note.adapter.NoteAdapterContract

interface NoteContract {
    interface View {

    }

    interface Presenter {
        var view: View
        var noteAdapterView: NoteAdapterContract.View
        var noteAdapterModel: NoteAdapterContract.Model

        fun loadNoteList()
    }
}