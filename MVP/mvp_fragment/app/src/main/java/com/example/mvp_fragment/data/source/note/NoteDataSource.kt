package com.example.mvp_fragment.data.source.note

import com.example.mvp_fragment.data.NoteItem

interface NoteDataSource {
    suspend fun getNotes(): Result<List<NoteItem>>
    suspend fun saveNote(item: NoteItem)
    suspend fun getNote(noteId: String): Result<NoteItem>
    suspend fun updateNoteTitleDetail(noteId: String, title: String, detail: String)
}