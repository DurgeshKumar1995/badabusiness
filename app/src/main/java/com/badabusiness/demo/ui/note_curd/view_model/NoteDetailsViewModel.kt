package com.badabusiness.demo.ui.note_curd.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.badabusiness.demo.model.Note
import com.badabusiness.demo.repo.NoteRepo
import kotlinx.coroutines.launch

class NoteDetailsViewModel( private val noteRepo: NoteRepo) : ViewModel() {

    val data = noteRepo.getNotes().cachedIn(viewModelScope)


    private val _updatePoint = MutableLiveData<Boolean>()
    val refreshPoint :LiveData<Boolean> = _updatePoint

    fun addData(note: Note) {
        viewModelScope.launch {
            noteRepo.insert(note)
            update()
        }
    }

    fun update(note: Note) {
        viewModelScope.launch {
            noteRepo.update(note)
            update()
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            noteRepo.delete(note)
            update()
        }
    }

    private fun update(){
        _updatePoint.postValue(true)
    }

}