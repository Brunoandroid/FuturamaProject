package com.example.futuramaproject.data.repository

import com.example.futuramaproject.data.model.CharacterResponse
import com.example.futuramaproject.di.ApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    private val apiService: ApiService = mockk()
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = Repository(apiService)
    }

    @Test
    fun `getCharacters() should call ApiService and return correct data`() = runTest {
        val mockResponse = CharacterResponse(emptyList(), 1, 1, 10, 100)
        coEvery { apiService.getCharacters() } returns mockResponse

        val result = repository.getCharacters()

        coVerify { apiService.getCharacters() }

        assertEquals(100, result.total)
    }
}
