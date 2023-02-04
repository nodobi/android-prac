package com.example.mvp_fragment.view.addnote.contract

import android.util.Log
import android.view.MenuItem
import com.example.mvp_fragment.R
import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.data.source.note.LocalDataNotFoundException
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.data.source.note.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNotePresenter : AddNoteContract.Presenter {
    override lateinit var view: AddNoteContract.View
    override lateinit var noteRepository: NoteRepository
    var noteId: String? = null

    override var onNavClickFunc: ((Unit) -> Unit)? = { onNavClickFunc() }
    override var onToolbarItemClickFunc: ((MenuItem, String, String) -> Unit)? = { menuItem, title, detail -> onToolbarItemClickListener(menuItem, title, detail) }
    override fun updateNote() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = noteRepository.getNote(noteId!!)
            when (result) {
                is Result.Success<NoteItem> -> {
                    view.setTitle(result.data.title)
                    view.setDetail(result.data.detail)
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

    private fun onToolbarItemClickListener(menuItem: MenuItem, title: String, detail: String) {
        CoroutineScope(Dispatchers.Main).launch {
            if(isEditNote()) {
                Log.d("hyeok", "noteId: $noteId, menuItemId: ${menuItem.itemId}")
                when(menuItem.itemId) {
                    R.id.item_editnote_delete -> {
                        noteRepository.deleteNote(noteId!!)
                    }
                    R.id.item_editnote_confirm -> {
                        noteRepository.updateNoteTitleDetail(noteId!!, title, detail)
                    }
                }
            }
            else {
                noteRepository.saveNote(NoteItem(title, detail))
            }
            view.popBackFragment(true)
        }
    }

    private fun onNavClickFunc() {
        view.popBackFragment(false)
    }

    override fun isEditNote() : Boolean {
        return noteId != null
    }
}