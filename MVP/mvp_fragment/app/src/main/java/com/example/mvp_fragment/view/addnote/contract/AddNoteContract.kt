package com.example.mvp_fragment.view.addnote.contract

import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.data.source.note.NoteRepository

interface AddNoteContract {
    interface View {
        fun popBackFragment()

    }

    interface Presenter {
        var view: View
        var noteRepository: NoteRepository

        var onNavClickFunc: ((Unit) -> Unit)?
        var onToolbarItemClickFunc: ((NoteItem) -> Unit)?

    }
}