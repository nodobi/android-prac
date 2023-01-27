package com.example.mvp_fragment.data.source.note

object NoteRepository : NoteDataSource {
    private val noteLocalDataSource: NoteLocalDataSource = NoteLocalDataSource

    override fun getNoteItems(count: Int, callback: NoteDataSource.LoadNoteCallback?) {
        noteLocalDataSource.getNoteItems(count, callback)
    }
}