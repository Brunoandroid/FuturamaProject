package com.example.futuramaproject.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.futuramaproject.R
import com.example.futuramaproject.components.LoadImageLocal

@Composable
fun FullScreenImageDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            LoadImageLocal(
                imageResId = R.drawable.futurama, isFillMaxSize = true
            )
            IconButton(modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 40.dp),
                onClick = { onDismiss() }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "")
            }
        }
    }
}