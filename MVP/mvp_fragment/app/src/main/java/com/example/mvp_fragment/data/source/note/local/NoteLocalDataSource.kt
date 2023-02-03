package com.example.mvp_fragment.data.source.note.local

import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.data.source.note.LocalDataNotFoundException
import com.example.mvp_fragment.data.source.note.NoteDataSource
import com.example.mvp_fragment.data.source.note.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object NoteLocalDataSource : NoteDataSource {
    lateinit var noteDao: NoteDao
    override suspend fun getNotes(): Result<List<NoteItem>> {
        return withContext(Dispatchers.IO) {
            val noteList = noteDao.getNotes()
            if(noteList != null) {
                Result.Success(noteList)
            } else {
                Result.Error(LocalDataNotFoundException())
            }
        }
    }

    override suspend fun saveNote(item: NoteItem) {
        withContext(Dispatchers.IO) {
            noteDao.insertNote(item)
        }
    }


}