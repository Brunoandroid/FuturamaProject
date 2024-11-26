package com.example.futuramaproject.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadImage(
    imageResId: Int,
    isFillMaxSize: Boolean = false,
    size: Dp = 100.dp,
    onClick: (() -> Unit)? = null
) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        modifier = Modifier
            .then(if (isFillMaxSize) Modifier.fillMaxSize() else Modifier.size(size))
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onClick?.invoke() })
            }
    )
}