package com.example.mvp_fragment.view.addnote.contract

import android.view.MenuItem
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.view.base.BaseContract

interface AddNoteContract {
    interface View : BaseContract.View {
        fun popBackFragment(isAdded: Boolean)
        fun showLoadError()
        fun setTitle(title: String)
        fun setDetail(detail: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        var noteRepository: NoteRepository

        var onNavClickFunc: ((Unit) -> Unit)?
        var onToolbarItemClickFunc: ((MenuItem, String, String) -> Unit)?

        fun updateNote()
        fun isEditNote(): Boolean
    }
}