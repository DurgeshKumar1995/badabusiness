package com.badabusiness.demo.repo

import androidx.paging.PagingData
import com.badabusiness.demo.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepo {

    suspend fun insert(item: Note): Long
    suspend fun delete(item: Note): Int
    suspend fun update(item: Note): Int
    fun getNotes(): Flow<PagingData<Note>>
}