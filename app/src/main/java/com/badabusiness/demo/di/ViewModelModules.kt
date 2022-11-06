package com.badabusiness.demo.di



import com.badabusiness.demo.db.NoteDao
import com.badabusiness.demo.impl.NoteImpl
import com.badabusiness.demo.repo.NotePagerRepo
import com.badabusiness.demo.repo.NoteRepo
import com.badabusiness.demo.ui.note_curd.view_model.NoteDetailsViewModel
import com.badabusiness.demo.ui.note_list.view_model.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


object ViewModelModules {

    val viewModels: Module = module {

        single { provideNotePagerRepo(get()) }
        single<NoteRepo> { NoteImpl(get()) }
        viewModel { MainViewModel(get()) }
        viewModel { NoteDetailsViewModel(get()) }


    }

    fun provideNotePagerRepo(daoAPI: NoteDao): NotePagerRepo = NotePagerRepo(daoAPI)



}

