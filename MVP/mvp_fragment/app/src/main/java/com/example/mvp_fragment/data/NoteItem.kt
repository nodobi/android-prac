package com.example.mvp_fragment.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "note_table")
data class NoteItem constructor (
    @ColumnInfo("title") var title: String,
    @ColumnInfo("detail") var detail: String,
    @PrimaryKey
    @ColumnInfo("id") var id: String = UUID.randomUUID().toString()
) {
    @ColumnInfo(name = "favorite")
    var isFavorite = false
}