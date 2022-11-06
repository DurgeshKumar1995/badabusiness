package com.badabusiness.demo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.badabusiness.demo.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao
}