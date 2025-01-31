package com.example.futuramaproject.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.futuramaproject.R
import com.example.futuramaproject.ui.theme.Dimens

@Composable
fun LoadImageLocal(
    imageResId: Int,
    isFillMaxSize: Boolean = false,
    size: Dp = Dimens.SizeXLarge,
    iconColor:  Color?= null,
    onClick: (() -> Unit)? = null
) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        modifier = Modifier
            .then(if (isFillMaxSize) Modifier.fillMaxSize() else Modifier.size(size))
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onClick?.invoke() })
            },
        colorFilter = iconColor?.let { ColorFilter.tint(it) }
    )
}

@Composable
fun LoadImageUrl(imageUrl: String?, size: Dp, isRoundedImage: Boolean = false, radius: Dp? = null) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.futurama)
            .fallback(R.drawable.futurama)
            .build(),
        modifier = Modifier.size(size)
            .let { if (isRoundedImage) it.clip(RoundedCornerShape(radius ?: 0.dp)) else it},
        contentScale = ContentScale.Crop,
        contentDescription = "",
    )
}