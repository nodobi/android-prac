package com.example.mvp_fragment.view.note.contract

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.view.base.BaseContract
import com.example.mvp_fragment.view.note.adapter.NoteAdapterContract

interface NoteContract {
    interface View : BaseContract.View {
        fun changeFragment(fragment: Fragment)
        fun registerFragmentResultListener(requestKey: String, listener: ((String, Bundle) -> Unit))
        fun showToast(text: String)
        fun showAddError()
        fun showAddSucess()
        fun showLoadError()
    }

    interface Presenter : BaseContract.Presenter<View> {
        var noteAdapterView: NoteAdapterContract.View?
        var noteAdapterModel: NoteAdapterContract.Model
        var noteRepository: NoteRepository

        var onFabClickFunc: ((android.view.View) -> Unit)?

        fun loadNoteList()
        fun initFragmentResultListener()
    }
}