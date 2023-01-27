package com.example.mvp_fragment.view.note.contract

import com.example.mvp_fragment.view.note.adapter.NoteAdapterContract

class NotePresenter: NoteContract.Presenter {
    override lateinit var view: NoteContract.View
    override lateinit var noteAdapterView: NoteAdapterContract.View
    override lateinit var noteAdapterModel: NoteAdapterContract.Model

    override fun loadNoteList() {
        val tempNoteList = arrayListOf<String>().apply {
            for(i in 1 .. 10) {
                add("tempNote $i")
            }
        }
        noteAdapterModel.updateNoteList(tempNoteList)
        noteAdapterView.notifyAdapter()
    }
}