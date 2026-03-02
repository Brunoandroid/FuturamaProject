package com.example.futuramaproject.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.futuramaproject.R
import com.example.futuramaproject.ui.theme.LocalThemeToggle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FuturamaAppBar(title: Int? = null) {
    val themeToggle = LocalThemeToggle.current

    TopAppBar(
        title = { Text(text = stringResource(title ?: R.string.app_name)) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        actions = {
            IconButton(onClick = { themeToggle.toggle() }) {
                Icon(
                    imageVector = if (themeToggle.isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                    contentDescription = stringResource(
                        if (themeToggle.isDarkTheme) R.string.app_name else R.string.app_name
                    ),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    )
}
