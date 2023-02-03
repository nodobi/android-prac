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
    override var noteAdapterView: NoteAdapterContract.View? = null
        set(value) {
            field = value
            field?.onItemClick = { onItemClickListener(it) }
        }

    override lateinit var noteAdapterModel: NoteAdapterContract.Model
    override lateinit var noteRepository: NoteRepository
    override var onFabClickFunc: ((View) -> Unit)? = { onFabClickListener() }

    override fun loadNoteList() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = noteRepository.getNotes()
            when (result) {
                is Result.Success<List<NoteItem>> -> {
                    noteAdapterModel.updateNoteList(result.data)
                    noteAdapterView?.notifyAdapter()
                }
                is Result.Error -> {
                    when (result.exception) {
                        is LocalDataNotFoundException -> {
                            view.showLoadError()
                        }
                    }
                }
            }
        }
    }

    override fun initFragmentResultListener() {
        view.registerFragmentResultListener(AddNoteFragment.REQUEST_ADD_NOTE) { requestKey: String, bundle: Bundle ->
            val result = bundle.getInt(AddNoteFragment.EXTRA_RESULT)
            when (result) {
                AddNoteFragment.RESULT_OK -> {
                    view.showAddSucess()
                    loadNoteList()
                    noteAdapterView?.notifyAdapter()
                }
                AddNoteFragment.RESULT_CANDELED -> {
                    view.showAddError()
                }
            }
        }
    }

    private fun onFabClickListener() {
        view.changeFragment(AddNoteFragment(null))
    }

    private fun onItemClickListener(noteId: String) {
        view.changeFragment(AddNoteFragment(noteId))
    }
}