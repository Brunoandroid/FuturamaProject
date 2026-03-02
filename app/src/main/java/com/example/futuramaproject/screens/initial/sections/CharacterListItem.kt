package com.example.futuramaproject.screens.initial.sections

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.futuramaproject.R
import com.example.futuramaproject.components.CustomText
import com.example.futuramaproject.components.LoadImageLocal
import com.example.futuramaproject.components.LoadImageUrl
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.ui.theme.Blue500
import com.example.futuramaproject.ui.theme.Dimens
import com.example.futuramaproject.ui.theme.Green500
import com.example.futuramaproject.ui.theme.Purple500
import com.example.futuramaproject.ui.theme.Red500
import com.example.futuramaproject.ui.theme.Yellow500

@Composable
fun CharacterListItem(
    character: CharacterItem,
    onClick: (() -> Unit)
) {
    val statusColor = when (character.status.lowercase()) {
        "alive" -> Green500
        "dead" -> Red500
        else -> Yellow500
    }
    Card(
        onClick = { onClick.invoke() },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimens.RadiusMedium),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(Dimens.StrokeSmall, color = MaterialTheme.colorScheme.outline),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.ElevationSmall)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoadImageUrl(
                imageUrl = character.image,
                size = Dimens.SizeXXLarge,
                isRoundedImage = true,
                radius = Dimens.RadiusMedium
            )
            Spacer(modifier = Modifier.width(Dimens.MarginLarge))
            Column(
                modifier = Modifier.height(Dimens.SizeXXLarge),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                CustomText(
                    text = character.name,
                    fontSize = Dimens.FontSizeXLarge,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(Dimens.MarginSmall))
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.SpaceEvenly) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        LoadImageLocal(
                            imageResId = R.drawable.circle_dot,
                            size = Dimens.SizeSmall,
                            iconColor = statusColor
                        )
                        Spacer(modifier = Modifier.width(Dimens.MarginSmall))
                        CustomText(
                            text = character.status,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        LoadImageLocal(
                            imageResId = R.drawable.users,
                            size = Dimens.SizeSmall,
                            iconColor = Blue500
                        )
                        Spacer(modifier = Modifier.width(Dimens.MarginSmall))
                        CustomText(
                            text = character.species,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        LoadImageLocal(
                            imageResId = R.drawable.user,
                            size = Dimens.SizeSmall,
                            iconColor = Purple500
                        )
                        Spacer(modifier = Modifier.width(Dimens.MarginSmall))
                        CustomText(
                            text = character.gender,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        )
                    }
                }
            }
        }
    }
}
