package com.example.mvp_fragment.data.source.note

import com.example.mvp_fragment.data.source.note.local.NoteLocalDataSource

object NoteRepository : NoteDataSource {
    lateinit var noteLocalDataSource: NoteLocalDataSource

    override fun getNoteItems(count: Int, callback: NoteDataSource.LoadNoteCallback?) {
        noteLocalDataSource.getNoteItems(count, callback)
    }
}