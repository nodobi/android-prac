package com.example.mvp_fragment.data.source.note

import com.example.mvp_fragment.data.NoteItem

object NoteLocalDataSource : NoteDataSource {
    override fun getNoteItems(count: Int, callback: NoteDataSource.LoadNoteCallback?) {
        val tempNoteList = arrayListOf<NoteItem>().apply {
            for (i in 1..count) {
                val num = (Math.random() * (count * 2)).toInt()
                add(NoteItem("title $num", "temp detail $num"))
            }
        }
        callback?.onNotesLoaded(tempNoteList)
    }
}