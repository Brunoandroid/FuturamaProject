package com.example.futuramaproject.screens.detail.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.futuramaproject.R
import com.example.futuramaproject.components.CustomText
import com.example.futuramaproject.components.LoadImageUrl
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.ui.theme.Dimens
import com.example.futuramaproject.ui.theme.White

@Composable
fun CharacterInfoSection(characterItem: CharacterItem?) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        LoadImageUrl(
            imageUrl = characterItem?.image,
            size = Dimens.SizeXXXLarge,
            isRoundedImage = true,
            radius = Dimens.RadiusMedium
        )
    }

    Spacer(modifier = Modifier.height(Dimens.MarginLarge))

    CustomText(text = characterItem?.name, fontSize = Dimens.FontSizeXXLarge)

    Spacer(modifier = Modifier.height(Dimens.MarginLarge))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimens.RadiusMedium),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PaddingMedium)
        ) {
            InfoRow(label = stringResource(id = R.string.status_label), value = characterItem?.status)
            HorizontalDivider()
            InfoRow(label = stringResource(id = R.string.species_label), value = characterItem?.species)
            HorizontalDivider()
            InfoRow(label = stringResource(id = R.string.gender_label), value = characterItem?.gender)
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = value ?: "", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        }
    }
}