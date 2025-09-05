package com.example.futuramaproject.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.futuramaproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FuturamaAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}