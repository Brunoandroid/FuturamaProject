package com.example.futuramaproject.di

import retrofit2.http.GET

interface ApiService {
    @GET("/fact")
    suspend fun getItems(): Any?
}