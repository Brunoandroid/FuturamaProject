package com.example.futuramaproject.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.di.ApiService
import java.io.IOException

class CharacterPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, CharacterItem>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterItem> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getCharacters(page = page, size = params.loadSize)
            val characterItems = response.characterItems

            LoadResult.Page(
                data = characterItems,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page < response.pages) page + 1 else null
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
