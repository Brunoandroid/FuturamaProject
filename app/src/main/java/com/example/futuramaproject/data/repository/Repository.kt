package com.example.futuramaproject.data.repository

import com.example.futuramaproject.di.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getCharacters() = apiService.getCharacters()
}