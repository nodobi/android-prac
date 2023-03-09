package com.example.mvp_fragment.data.source.note

import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.data.source.note.local.NoteLocalDataSource

object NoteRepository : NoteDataSource {
    lateinit var noteLocalDataSource: NoteLocalDataSource


    override suspend fun getNotes(): Result<List<NoteItem>> {
        return noteLocalDataSource.getNotes()
    }

    override suspend fun getNotes(date: String): Result<List<NoteItem>> {
        return noteLocalDataSource.getNotes(date)
    }

    override suspend fun saveNote(item: NoteItem) {
        noteLocalDataSource.saveNote(item)
    }

    override suspend fun getNote(noteId: String): Result<NoteItem> {
        return noteLocalDataSource.getNote(noteId)
    }

    override suspend fun updateNoteTitleDetail(noteId: String, title: String, detail: String) {
        noteLocalDataSource.updateNoteTitleDetail(noteId, title, detail)
    }

    override suspend fun deleteNote(noteId: String) {
        noteLocalDataSource.deleteNote(noteId)
    }

    override suspend fun setFavorite(noteId: String, isFavorite: Boolean) {
        noteLocalDataSource.setFavorite(noteId, isFavorite)
    }

    override suspend fun getFavoriteNotes(): Result<List<NoteItem>> {
        return noteLocalDataSource.getFavoriteNotes()
    }
}