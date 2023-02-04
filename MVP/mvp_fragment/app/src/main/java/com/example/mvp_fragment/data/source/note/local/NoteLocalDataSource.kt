package com.example.mvp_fragment.data.source.note.local

import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.data.source.note.LocalDataNotFoundException
import com.example.mvp_fragment.data.source.note.NoteDataSource
import com.example.mvp_fragment.data.source.note.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object NoteLocalDataSource : NoteDataSource {
    lateinit var noteDao: NoteDao
    override suspend fun getNotes(): Result<List<NoteItem>> = withContext(Dispatchers.IO) {
        val noteList = noteDao.getNotes()
        if (noteList == null) {
            Result.Error(LocalDataNotFoundException())
        } else {
            Result.Success(noteList)
        }
    }

    override suspend fun saveNote(item: NoteItem) = withContext(Dispatchers.IO) {
        noteDao.insertNote(item)
    }

    override suspend fun getNote(noteId: String): Result<NoteItem> = withContext(Dispatchers.IO) {
        val noteItem = noteDao.getNote(noteId)
        if (noteItem == null) {
            Result.Error(LocalDataNotFoundException())
        } else {
            Result.Success(noteItem)
        }
    }

    override suspend fun updateNoteTitleDetail(noteId: String, title: String, detail: String) =
        withContext(Dispatchers.IO) {
            noteDao.updateNoteTitleDetail(noteId, title, detail)
        }

    override suspend fun deleteNote(noteId: String) = withContext(Dispatchers.IO) {
        noteDao.deleteNote(noteId)
    }


}