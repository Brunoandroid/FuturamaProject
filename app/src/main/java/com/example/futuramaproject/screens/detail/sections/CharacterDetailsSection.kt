package com.example.futuramaproject.screens.detail.sections

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.futuramaproject.R
import com.example.futuramaproject.components.CustomText
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.ui.theme.Dimens
import com.example.futuramaproject.ui.theme.White

@Composable
fun CharacterDetailsSection(characterItem: CharacterItem?) {
    CustomText(
        text = stringResource(id = R.string.about_label),
        fontWeight = FontWeight.Bold,
        fontSize = Dimens.FontSizeXLarge,
    )
    Spacer(modifier = Modifier.height(Dimens.MarginSmall))
    Text(
        text = stringResource(id = R.string.about_description, characterItem?.name ?: ""),
        style = MaterialTheme.typography.bodyMedium,
    )

    Spacer(modifier = Modifier.height(Dimens.MarginLarge))

    CustomText(
        text = stringResource(id = R.string.details_label),
        fontWeight = FontWeight.Bold,
        fontSize = Dimens.FontSizeXLarge,
    )
    Spacer(modifier = Modifier.height(Dimens.MarginSmall))

    Column(modifier = Modifier.fillMaxWidth()) {
        DetailChipCard(
            title = stringResource(id = R.string.earth),
            subtitle = stringResource(id = R.string.home_planet),
        )
        Spacer(modifier = Modifier.height(Dimens.MarginSmall))
        DetailChipCard(
            title = stringResource(id = R.string.space_pilot_3000),
            subtitle = stringResource(id = R.string.first_appearance),
        )
    }
}

@Composable
private fun DetailChipCard(title: String, subtitle: String, isBorderStroke: Boolean = true) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
                then if (isBorderStroke) Modifier.border(
            width = Dimens.StrokeSmall,
            shape = RoundedCornerShape(Dimens.RadiusMedium),
            color = MaterialTheme.colorScheme.outline

        ) else Modifier,
        shape = RoundedCornerShape(Dimens.RadiusMedium),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PaddingMedium)
        ) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}