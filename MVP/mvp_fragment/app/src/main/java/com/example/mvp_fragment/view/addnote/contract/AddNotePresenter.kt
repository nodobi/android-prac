package com.example.mvp_fragment.view.addnote.contract

import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.data.source.note.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNotePresenter : AddNoteContract.Presenter {
    override lateinit var view: AddNoteContract.View
    override lateinit var noteRepository: NoteRepository

    override var onNavClickFunc: ((Unit) -> Unit)? = { onNavClickFunc() }
    override var onToolbarItemClickFunc: ((NoteItem) -> Unit)? = { onToolbarItemClickListener(it) }

    private fun onToolbarItemClickListener(item: NoteItem) {
        CoroutineScope(Dispatchers.Main).launch {
            noteRepository.saveNote(item)
        }
        view.popBackFragment()
    }

    private fun onNavClickFunc() {
        view.popBackFragment()
    }
}