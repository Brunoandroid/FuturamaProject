package com.example.futuramaproject.screens.initial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futuramaproject.R
import com.example.futuramaproject.components.FuturamaAppBar
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.screens.initial.sections.CharacterListScreen
import com.example.futuramaproject.ui.theme.Dimens
import com.example.futuramaproject.ui.theme.White

@Composable
fun InitialScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = { FuturamaAppBar() }, containerColor = White
    ) { paddingValues ->
        ContentPage(
            paddingValues = paddingValues,
            navHostController = navHostController
        )
    }
}

@Composable
private fun ContentPage(
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    val viewModel: InitialViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.observeAsState(true)
    val items by viewModel.items.observeAsState()

    when {
        isLoading -> InitialScreenLoading()
        items.isNullOrEmpty() -> InitialEmptyScreen()
        else -> CharacterListScreen(
            paddingValues, navHostController, items ?: listOf()
        )
    }
}

@Composable
fun InitialScreenLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(Dimens.SizeLarge),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun InitialEmptyScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.no_data_returned),
            color = MaterialTheme.colorScheme.primary,
            fontSize = Dimens.FontSizeXXXLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreview() {
    val fakeCharacters = listOf(
        CharacterItem("2024-01-01", "Male", 1, "", "Bender", "Robot", "Alive"),
        CharacterItem("2024-01-02", "Female", 2, "", "Leela", "Mutant", "Alive"),
        CharacterItem("2024-01-03", "Male", 3, "", "Fry", "Human", "Alive")
    )
    CharacterListScreen(
        PaddingValues(Dimens.PaddingMedium), navHostController = rememberNavController(), fakeCharacters
    )
}