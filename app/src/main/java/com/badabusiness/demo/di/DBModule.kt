package com.badabusiness.demo.di

import androidx.room.Room
import com.badabusiness.demo.db.NoteDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object DBModule {

    private const val DATABASE_NAME  = "notes_db"
    val notesAppModule = module {

        // Room Database
        single { Room.databaseBuilder(androidApplication(), NoteDatabase::class.java, DATABASE_NAME).build() }

        // BirdsDAO
        single { get<NoteDatabase>().noteDao() }
    }
}