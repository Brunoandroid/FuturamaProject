package com.example.futuramaproject.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.futuramaproject.R

@Composable
fun CustomText(
    text: String,
    color: Int = R.color.black,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = { onClick?.invoke() })
        },
        text = text,
        color = colorResource(color),
        fontSize = fontSize,
        fontWeight = fontWeight
    )
}