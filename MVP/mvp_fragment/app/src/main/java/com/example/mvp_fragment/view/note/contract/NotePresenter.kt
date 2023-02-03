package com.example.mvp_fragment.view.note.contract

import android.os.Bundle
import android.view.View
import com.example.mvp_fragment.data.source.note.Result
import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.data.source.note.LocalDataNotFoundException
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.view.addnote.AddNoteFragment
import com.example.mvp_fragment.view.note.adapter.NoteAdapterContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.*

class NotePresenter : NoteContract.Presenter {
    override lateinit var view: NoteContract.View
    override lateinit var noteAdapterView: NoteAdapterContract.View
    override lateinit var noteAdapterModel: NoteAdapterContract.Model
    override lateinit var noteRepository: NoteRepository
    override var onFabClickFunc: ((View) -> Unit)? = { onFabClickListener() }
    override var fragmentResultFunc: ((String, Bundle) -> Unit)? = { requestKey, bundle -> fragmentResultListener(requestKey, bundle)}

    override fun loadNoteList() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = noteRepository.getNotes()
            when(result) {
                is Result.Success<List<NoteItem>> -> {
                    noteAdapterModel.updateNoteList(result.data)
                    noteAdapterView.notifyAdapter()
                }
                is Result.Error -> {
                    when(result.exception) {
                        is LocalDataNotFoundException -> {
                            view.showLoadError()
                        }
                    }
                }
            }
        }
    }

    private fun fragmentResultListener(requestKey: String, bundle: Bundle) {
        if(requestKey.equals(AddNoteFragment.REQUEST_ADD_NOTE)) {
            val result = bundle.getInt(AddNoteFragment.EXTRA_RESULT)
            when(result) {
                AddNoteFragment.RESULT_OK -> {
                    view.showAddSucess()
                    noteAdapterView.notifyAdapter()
                }
                AddNoteFragment.RESULT_CANDELED -> {
                    view.showAddError()
                }
            }
        }
    }

    private fun onFabClickListener() {
        view.changeFragment(AddNoteFragment())
    }
}