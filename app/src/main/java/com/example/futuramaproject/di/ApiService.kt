package com.example.futuramaproject.di

import com.example.futuramaproject.data.model.CharacterResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/api/characters")
    suspend fun getCharacters(): CharacterResponse
}