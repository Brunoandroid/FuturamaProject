package com.example.futuramaproject.screens.home

import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel()
    }

    @Test
    fun `showDialog() should update isShowDialog state to true`() = runTest {
        viewModel.showDialog()
        val state = viewModel.uiState.value
        assertTrue(state.isShowDialog)
    }

    @Test
    fun `hideDialog() should update isShowDialog state to false`() = runTest {
        viewModel.hideDialog()
        val state = viewModel.uiState.value
        assertFalse(state.isShowDialog)
    }
}