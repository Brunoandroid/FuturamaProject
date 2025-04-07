package com.example.futuramaproject.screens.initial

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.data.model.CharacterResponse
import com.example.futuramaproject.data.repository.Repository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class InitialViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository: Repository = mockk()
    private lateinit var viewModel: InitialViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        coEvery { repository.getCharacters() } returns CharacterResponse(
            characterItems = listOf(CharacterItem("2024-01-01", "Male", 1, "url", "Bender", "Robot", "Alive")),
            page = 1, pages = 1, size = 1, total = 1
        )

        viewModel = InitialViewModel(repository)
    }

    @Test
    fun `fetch() should update items LiveData`() = runTest {
        viewModel.items.observeForever { }

        assertEquals(1, viewModel.items.value?.size)
        assertEquals("Bender", viewModel.items.value?.first()?.name)
    }

    @Test
    fun `fetch() should update isLoading LiveData`() = runTest {
        viewModel.isLoading.observeForever { }

        assertEquals(false, viewModel.isLoading.value)
    }
}
