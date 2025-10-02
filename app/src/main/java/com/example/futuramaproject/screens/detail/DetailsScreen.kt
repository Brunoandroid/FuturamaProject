package com.example.futuramaproject.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.futuramaproject.R
import com.example.futuramaproject.components.CircularLoading
import com.example.futuramaproject.components.EmptyScreen
import com.example.futuramaproject.components.FuturamaAppBar
import com.example.futuramaproject.screens.detail.sections.CharacterDetailsSection
import com.example.futuramaproject.screens.detail.sections.CharacterInfoSection
import com.example.futuramaproject.ui.theme.Dimens

@Composable
fun DetailsScreen() {
    Scaffold(
        topBar = { FuturamaAppBar(title = R.string.details_character) },
    ) { paddingValues ->
        DetailsContent(
            paddingValues
        )
    }
}

@Composable
private fun DetailsContent(
    paddingValues: PaddingValues
) {
    val viewModel: DetailsViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.observeAsState(initial = true)
    val characterItem by viewModel.character.observeAsState()

    when {
        isLoading -> CircularLoading()
        characterItem == null -> EmptyScreen()
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(Dimens.PaddingMedium),
            ) {
                CharacterInfoSection(characterItem)
                Spacer(modifier = Modifier.height(Dimens.PaddingLarge))
                CharacterDetailsSection(characterItem)
            }
        }
    }
}