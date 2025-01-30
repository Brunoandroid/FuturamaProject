package com.example.futuramaproject.data.model


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("items")
    val characterItems: List<CharacterItem>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("total")
    val total: Int
)