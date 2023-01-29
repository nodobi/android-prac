package com.example.mvp_fragment.view.note.contract

import androidx.fragment.app.Fragment
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.view.note.adapter.NoteAdapterContract

interface NoteContract {
    interface View {
        fun changeFragment(fragment: Fragment)
    }

    interface Presenter {
        var view: View
        var noteAdapterView: NoteAdapterContract.View
        var noteAdapterModel: NoteAdapterContract.Model
        var noteRepository: NoteRepository

        var onFabClickFunc: ((android.view.View) -> Unit)?

        fun loadNoteList()
    }
}