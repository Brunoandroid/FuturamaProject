package com.example.futuramaproject.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futuramaproject.R
import com.example.futuramaproject.Screen
import com.example.futuramaproject.components.CustomText
import com.example.futuramaproject.components.LoadImageLocal
import com.example.futuramaproject.components.LoadImageUrl
import com.example.futuramaproject.data.model.CharacterItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(navHostController: NavHostController) {
    val viewModel: DetailViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.observeAsState(true)
    val items by viewModel.items.observeAsState()

    Scaffold(
        topBar = { AppBar() },
        containerColor = Color.White
    ) { paddingValues ->
        when {
            isLoading -> DetailScreenLoading()
            items.isNullOrEmpty() -> DetailsEmptyScreen()
            else -> CharacterListScreen(
                paddingValues,
                navHostController,
                items ?: listOf()
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun CharacterListScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    characters: List<CharacterItem>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(characters) { character ->
            CharacterListItem(navHostController, character)
        }
    }
}

@Composable
private fun CharacterListItem(
    navHostController: NavHostController,
    character: CharacterItem
) {
    val statusColor = when (character.status.lowercase()) {
        "alive" -> R.color.green_500
        "dead" -> R.color.red_500
        else -> R.color.yellow_500
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LoadImageUrl(
            imageUrl = character.image,
            size = 130.dp,
            isRoundedImage = true,
            radius = 16.dp
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            modifier = Modifier.height(130.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CustomText(
                onClick = {
                    navHostController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                text = character.name,
                color = R.color.black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Row {
                LoadImageLocal(
                    imageResId = R.drawable.circle_dot,
                    size = 16.dp,
                    iconColor = statusColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                CustomText(
                    text = character.status,
                    color = R.color.gray,
                )
            }
            Row {
                LoadImageLocal(
                    imageResId = R.drawable.users,
                    size = 16.dp,
                    iconColor = R.color.blue_500
                )
                Spacer(modifier = Modifier.width(8.dp))
                CustomText(
                    text = character.species,
                    color = R.color.gray,
                )
            }
            Row {
                LoadImageLocal(
                    imageResId = R.drawable.user,
                    size = 16.dp,
                    iconColor = R.color.purple_500
                )
                Spacer(modifier = Modifier.width(8.dp))
                CustomText(
                    text = character.gender,
                    color = R.color.gray,
                )
            }
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
    CharacterListScreen(PaddingValues(16.dp), navHostController = rememberNavController(), fakeCharacters)
}