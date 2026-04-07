package com.example.futuramaproject.screens.initial

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.futuramaproject.components.CircularLoading
import com.example.futuramaproject.components.EmptyScreen
import com.example.futuramaproject.components.FuturamaAppBar
import com.example.futuramaproject.screens.initial.sections.CharacterListScreen

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
    val items = viewModel.items.collectAsLazyPagingItems()

    when {
        items.loadState.refresh is LoadState.Loading -> CircularLoading()
        items.itemCount == 0 && items.loadState.refresh is LoadState.NotLoading -> EmptyScreen()
        else -> CharacterListScreen(
            paddingValues,
            navHostController,
            items,
            onItemClick = { item ->
                navHostController.navigate("detail_screen/${item}")
            }
        )
    }
}