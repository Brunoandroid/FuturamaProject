package com.example.futuramaproject.screens.initial

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futuramaproject.components.CircularLoading
import com.example.futuramaproject.components.EmptyScreen
import com.example.futuramaproject.components.FuturamaAppBar
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.screens.initial.sections.CharacterListScreen
import com.example.futuramaproject.ui.theme.Dimens

@Composable
fun InitialScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = { FuturamaAppBar() }, containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        InitialContent(
            paddingValues = paddingValues,
            navHostController = navHostController
        )
    }
}

@Composable
private fun InitialContent(
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    val viewModel: InitialViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.observeAsState(true)
    val items by viewModel.items.observeAsState()

    when {
        isLoading -> CircularLoading()
        items.isNullOrEmpty() -> EmptyScreen()
        else -> CharacterListScreen(
            paddingValues, navHostController, items ?: listOf()
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CharacterListScreenPreview() {
    val fakeCharacters = listOf(
        CharacterItem("2024-01-01", "Male", 1, "", "Bender", "Robot", "Alive"),
        CharacterItem("2024-01-02", "Female", 2, "", "Leela", "Mutant", "Alive"),
        CharacterItem("2024-01-03", "Male", 3, "", "Fry", "Human", "Alive")
    )
    CharacterListScreen(
        PaddingValues(Dimens.PaddingMedium), navHostController = rememberNavController(), fakeCharacters
    )
}