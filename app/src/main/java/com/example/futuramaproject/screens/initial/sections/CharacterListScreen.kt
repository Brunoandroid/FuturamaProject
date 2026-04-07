package com.example.futuramaproject.screens.initial.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.futuramaproject.R
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.ui.theme.Dimens

@Composable
fun CharacterListScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    characters: LazyPagingItems<CharacterItem>,
    onItemClick: (Int) -> Unit,
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
        items(characters.itemCount) { index ->
            val character = characters[index]
            character?.let {
                CharacterListItem(
                    character = it,
                    onClick = { onItemClick(it.id) }
                )
            }
        }

        when (val state = characters.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Dimens.PaddingMedium),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(Dimens.SizeLarge),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Dimens.PaddingMedium),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.error_loading_more),
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(Dimens.MarginSmall))
                        Button(onClick = { characters.retry() }) {
                            Text(text = stringResource(id = R.string.retry_label))
                        }
                    }
                }
            }

            else -> {}
        }
    }
}