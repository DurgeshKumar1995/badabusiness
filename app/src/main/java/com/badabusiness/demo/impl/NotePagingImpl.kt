package com.badabusiness.demo.impl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.badabusiness.demo.db.NoteDao
import com.badabusiness.demo.model.Note
import kotlinx.coroutines.delay

class NotePagingImpl(
    private val dao: NoteDao
) : PagingSource<Int, Note>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Note> {
        val page = params.key ?: 0

        return try {
            val entities = dao.getNoteList(params.loadSize, page * params.loadSize)

            // simulate page loading
            if (page != 0) delay(1000)

            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Note>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}