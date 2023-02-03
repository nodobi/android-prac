package com.example.mvp_fragment.view.addnote.contract

import com.example.mvp_fragment.data.source.note.NoteRepository

interface AddNoteContract {
    interface View {
        fun popBackFragment(isAdded: Boolean)
        fun showLoadError()
        fun setTitle(title: String)
        fun setDetail(detail: String)

    }

    interface Presenter {
        var view: View
        var noteRepository: NoteRepository

        var onNavClickFunc: ((Unit) -> Unit)?
        var onToolbarItemClickFunc: ((String, String) -> Unit)?

        fun updateNote()
        fun isEditNote(): Boolean
    }
}