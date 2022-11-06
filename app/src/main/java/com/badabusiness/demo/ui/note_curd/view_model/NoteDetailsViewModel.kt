package com.badabusiness.demo.ui.note_curd.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.badabusiness.demo.model.Note
import com.badabusiness.demo.repo.NoteRepo
import kotlinx.coroutines.launch

class NoteDetailsViewModel( private val noteRepo: NoteRepo) : ViewModel() {


    private val _updatePoint = MutableLiveData<Boolean>()
    val refreshPoint :LiveData<Boolean> = _updatePoint

    fun addData(note: Note) {
        viewModelScope.launch {
            val data =noteRepo.insert(note)
            update(data>0)
        }
    }

    fun update(note: Note) {
        viewModelScope.launch {
            val data =noteRepo.update(note)
            update(data>0)
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            val data = noteRepo.delete(note)
            update(data>0)
        }
    }

    private fun update(boolean: Boolean=true){
        _updatePoint.postValue(boolean)
    }

}