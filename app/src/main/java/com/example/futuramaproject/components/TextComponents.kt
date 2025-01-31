package com.example.futuramaproject.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.example.futuramaproject.ui.theme.Black
import com.example.futuramaproject.ui.theme.Dimens

@Composable
fun CustomText(
    text: String,
    color: Color = Black,
    fontSize: TextUnit = Dimens.FontSizeMedium,
    fontWeight: FontWeight = FontWeight.Normal,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = { onClick?.invoke() })
        },
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight
    )
}