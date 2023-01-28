package com.example.mvp_fragment.view.note.contract

import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.data.source.note.NoteDataSource
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.view.note.adapter.NoteAdapterContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NotePresenter : NoteContract.Presenter {
    override lateinit var view: NoteContract.View
    override lateinit var noteAdapterView: NoteAdapterContract.View
    override lateinit var noteAdapterModel: NoteAdapterContract.Model
    override lateinit var noteRepository: NoteRepository

    override fun loadNoteList() {
        var tempNoteList: ArrayList<NoteItem> = arrayListOf()

        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async {
                noteRepository.getNoteItems(10, object : NoteDataSource.LoadNoteCallback {
                    override fun onNotesLoaded(noteList: ArrayList<NoteItem>) {
                        tempNoteList = noteList
                    }
                })
            }.await()
            noteAdapterModel.updateNoteList(tempNoteList)
            noteAdapterView.notifyAdapter()
        }

    }
}