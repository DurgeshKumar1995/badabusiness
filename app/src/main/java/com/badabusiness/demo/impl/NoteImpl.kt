package com.badabusiness.demo.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.badabusiness.demo.db.NoteDao
import com.badabusiness.demo.model.Note
import com.badabusiness.demo.repo.NoteRepo

class NoteImpl(private val noteDao: NoteDao):NoteRepo {
    override suspend fun insert(item: Note) = noteDao.insert(item)

    override suspend fun delete(item: Note) = noteDao.delete(item)

    override suspend fun update(item: Note) = noteDao.update(item)

    override fun getNotes() =
         Pager(
            config = PagingConfig(
                pageSize = 15,
                enablePlaceholders = false,
                initialLoadSize = 15
            )){
            noteDao.getNotes()
        }.flow

}