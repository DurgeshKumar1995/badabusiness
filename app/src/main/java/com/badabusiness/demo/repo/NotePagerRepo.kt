package com.badabusiness.demo.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.badabusiness.demo.db.NoteDao
import com.badabusiness.demo.impl.NotePagingImpl
import com.badabusiness.demo.model.Note
import kotlinx.coroutines.flow.Flow


class NotePagerRepo(private val daoAPI: NoteDao) {

    fun getNotes(): Flow<PagingData<Note>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15,
                enablePlaceholders = false,
                initialLoadSize = 15
            ),
            pagingSourceFactory = { NotePagingImpl(daoAPI) }
        ).flow
    }

    suspend fun insert(item: Note) = daoAPI.insert(item)

    suspend fun delete(item: Note) = daoAPI.delete(item)

    suspend fun update(item: Note) = daoAPI.update(item)

}