package com.example.futuramaproject.screens.initial.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.ui.theme.Dimens

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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.MarginSmall),
        contentPadding = PaddingValues(
            horizontal = Dimens.PaddingMedium,
            vertical = Dimens.MarginSmall
        )
    ) {
        items(characters) { character ->
            CharacterListItem(
                character = character,
                onClick = { navHostController.navigate("detail_screen/${character.id}") }
            )
        }
    }
}