package com.example.futuramaproject.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.futuramaproject.ui.theme.Black
import com.example.futuramaproject.ui.theme.Blue500
import com.example.futuramaproject.ui.theme.Dimens
import com.example.futuramaproject.ui.theme.Gray
import com.example.futuramaproject.ui.theme.Green500
import com.example.futuramaproject.ui.theme.Purple500
import com.example.futuramaproject.ui.theme.Red500
import com.example.futuramaproject.ui.theme.White
import com.example.futuramaproject.ui.theme.Yellow500

@Composable
fun DetailScreen(navHostController: NavHostController) {
    val viewModel: DetailViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.observeAsState(true)
    val items by viewModel.items.observeAsState()

    Scaffold(
        topBar = { AppBar() },
        containerColor = White
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
        "alive" -> Green500
        "dead" -> Red500
        else -> Yellow500
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.PaddingSmall)
    ) {
        LoadImageUrl(
            imageUrl = character.image,
            size = Dimens.SizeXXLarge,
            isRoundedImage = true,
            radius = Dimens.RadiusMedium
        )
        Spacer(modifier = Modifier.width(Dimens.MarginLarge))
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
                color = Black,
                fontSize = Dimens.FontSizeXLarge,
                fontWeight = FontWeight.Medium
            )
            Row {
                LoadImageLocal(
                    imageResId = R.drawable.circle_dot,
                    size = Dimens.SizeSmall,
                    iconColor = statusColor
                )
                Spacer(modifier = Modifier.width(Dimens.MarginSmall))
                CustomText(
                    text = character.status,
                    color = Gray,
                )
            }
            Row {
                LoadImageLocal(
                    imageResId = R.drawable.users,
                    size = Dimens.SizeSmall,
                    iconColor = Blue500
                )
                Spacer(modifier = Modifier.width(Dimens.MarginSmall))
                CustomText(
                    text = character.species,
                    color = Gray,
                )
            }
            Row {
                LoadImageLocal(
                    imageResId = R.drawable.user,
                    size = Dimens.SizeSmall,
                    iconColor = Purple500
                )
                Spacer(modifier = Modifier.width(Dimens.MarginSmall))
                CustomText(
                    text = character.gender,
                    color = Gray,
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
        CircularProgressIndicator(
            modifier = Modifier.size(Dimens.SizeLarge),
            color = MaterialTheme.colorScheme.primary
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
    CharacterListScreen(PaddingValues(Dimens.PaddingMedium), navHostController = rememberNavController(), fakeCharacters)
}