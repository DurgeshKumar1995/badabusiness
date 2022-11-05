package com.badabusiness.demo.db

import androidx.paging.PagingSource
import androidx.room.*
import com.badabusiness.demo.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getNoteList(limit: Int, offset: Int): List<Note>

    @Transaction
    @Query("SELECT * FROM note ORDER BY createdDate DESC")
    fun getNotes(): PagingSource<Int,Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Note): Long

    @Delete
    suspend fun delete(item: Note): Int

    @Update
    suspend fun update(item: Note): Int
}