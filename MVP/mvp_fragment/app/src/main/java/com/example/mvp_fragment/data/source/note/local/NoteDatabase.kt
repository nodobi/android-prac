package com.example.mvp_fragment.data.source.note.local

import android.content.Context
import androidx.room.*
import com.example.mvp_fragment.data.NoteItem

@Database(entities = [NoteItem::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {

        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "Notes.db"
                ).build()
            }
            return INSTANCE!!
        }
    }
}