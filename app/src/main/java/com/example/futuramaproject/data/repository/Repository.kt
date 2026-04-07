package com.example.futuramaproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.data.paging.CharacterPagingSource
import com.example.futuramaproject.di.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
) {
    fun getCharacters(): Flow<PagingData<CharacterItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSource(apiService) }
        ).flow
    }

    suspend fun getCharacterDetails(characterId: Int) = apiService.getCharacterDetails(characterId)
}