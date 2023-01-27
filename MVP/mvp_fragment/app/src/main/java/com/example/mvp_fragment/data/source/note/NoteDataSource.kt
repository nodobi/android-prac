package com.example.mvp_fragment.data.source.note

import com.example.mvp_fragment.data.NoteItem

interface NoteDataSource {
    interface LoadNoteCallback {
        fun onNotesLoaded(noteList: ArrayList<NoteItem>)
    }

    fun getNoteItems(count: Int, callback: LoadNoteCallback?)
}