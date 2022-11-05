package com.badabusiness.demo

import android.app.Application
import com.badabusiness.demo.di.DBModule
import com.badabusiness.demo.di.ViewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class NotesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NotesApplication)
            modules(DBModule.notesAppModule, ViewModelModules.viewModels)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}