package com.example.mvp_fragment.data.source.note

import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.data.source.note.local.NoteLocalDataSource

object NoteRepository : NoteDataSource {
    lateinit var noteLocalDataSource: NoteLocalDataSource


    override suspend fun getNotes(): Result<List<NoteItem>> {
        return noteLocalDataSource.getNotes()
    }

    override suspend fun saveNote(item: NoteItem) {
        noteLocalDataSource.saveNote(item)
    }
}