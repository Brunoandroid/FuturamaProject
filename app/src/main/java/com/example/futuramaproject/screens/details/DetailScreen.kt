package com.example.futuramaproject.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futuramaproject.Screen
import com.example.futuramaproject.data.model.CharacterItem

@Composable
fun DetailScreen(navHostController: NavHostController) {
    val viewModel: DetailViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.observeAsState(true)
    val items by viewModel.items.observeAsState()

    when {
        isLoading -> DetailScreenLoading()
        items.isNullOrEmpty() -> DetailsEmptyScreen()
        else -> CharacterListScreen(navHostController,items ?: listOf())
    }
}

@Composable
fun CharacterListScreen(navHostController: NavHostController, characters: List<CharacterItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(characters) { character ->
            Text(
                modifier = Modifier.clickable {
                    navHostController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                text = character.name,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun DetailScreenLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Carregando. . .",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DetailsEmptyScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Nenhum dado retornado",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 35.sp,
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
    CharacterListScreen(navHostController = rememberNavController(), fakeCharacters)
}