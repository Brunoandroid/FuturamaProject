package com.example.futuramaproject.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.futuramaproject.R
import com.example.futuramaproject.ui.theme.Dimens

@Composable
fun EmptyScreen() {
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