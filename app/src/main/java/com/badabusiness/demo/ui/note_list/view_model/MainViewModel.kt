package com.badabusiness.demo.ui.note_list.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.badabusiness.demo.model.Note
import com.badabusiness.demo.repo.NotePagerRepo
import com.badabusiness.demo.repo.NoteRepo
import kotlinx.coroutines.launch

class MainViewModel(private val notePagerRepo: NotePagerRepo,private val noteRepo: NoteRepo) : ViewModel() {

//    val data = notePagerRepo.getNotes().cachedIn(viewModelScope)
    val data = noteRepo.getNotes().cachedIn(viewModelScope)


    private val _refreshPoint = MutableLiveData<Boolean>()
    val refreshPoint :LiveData<Boolean> = _refreshPoint

    fun addData() {
        viewModelScope.launch {
            for (i in 0..100)
                notePagerRepo.insert(Note( title = "Data $i", description = "Test"))
        }
    }

    fun update(note: Note) {
        viewModelScope.launch {
//            notePagerRepo.update(note)
            noteRepo.update(note)
            refresh()
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
//            notePagerRepo.delete(note)
            noteRepo.delete(note)
            refresh()
        }
    }

    fun refresh(){
        _refreshPoint.postValue(true)
    }

}