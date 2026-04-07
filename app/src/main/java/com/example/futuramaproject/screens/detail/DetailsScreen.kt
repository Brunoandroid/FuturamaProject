package com.example.futuramaproject.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        CircularLoading()
    } else if (uiState.isError) {
        EmptyScreen()
    } else if (uiState.character != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Dimens.PaddingMedium)
                .verticalScroll(rememberScrollState()),
        ) {
            CharacterInfoSection(uiState.character)
            Spacer(modifier = Modifier.height(Dimens.PaddingMedium))
            CharacterDetailsSection(uiState.character)
        }
    }
}