package com.example.mvp_fragment.data.source.note.local

import com.example.mvp_fragment.data.source.note.NoteDataSource

object NoteLocalDataSource : NoteDataSource {
    lateinit var noteDao: NoteDao
    override fun getNoteItems(count: Int, callback: NoteDataSource.LoadNoteCallback?) {
        val noteList = ArrayList(noteDao.getNotes())
        callback?.onNotesLoaded(ArrayList(noteList))
    }
}