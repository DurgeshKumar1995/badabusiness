package com.badabusiness.demo.ui.note_list.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.badabusiness.demo.repo.NoteRepo

class MainViewModel( noteRepo: NoteRepo) : ViewModel() {

    val data = noteRepo.getNotes().cachedIn(viewModelScope)


    private val _refreshPoint = MutableLiveData<Boolean>()
    val refreshPoint :LiveData<Boolean> = _refreshPoint
}