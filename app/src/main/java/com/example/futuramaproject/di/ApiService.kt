package com.example.futuramaproject.di

import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): CharacterResponse

    @GET("characters/{id}")
    suspend fun getCharacterDetails(@Path("id") characterId: Int): CharacterItem
}