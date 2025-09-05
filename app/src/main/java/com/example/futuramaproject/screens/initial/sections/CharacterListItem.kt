package com.example.futuramaproject.screens.initial.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.futuramaproject.R
import com.example.futuramaproject.navigation.Screen
import com.example.futuramaproject.components.CustomText
import com.example.futuramaproject.components.LoadImageLocal
import com.example.futuramaproject.components.LoadImageUrl
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.ui.theme.*

@Composable
fun CharacterListItem(
    navHostController: NavHostController,
    character: CharacterItem,
    onClick: (() -> Unit)
) {
    val statusColor = when (character.status.lowercase()) {
        "alive" -> Green500
        "dead" -> Red500
        else -> Yellow500
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(Dimens.SizeXXLarge)
            .padding(Dimens.PaddingSmall)
            .clickable { onClick.invoke() },
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
                onClick = {
                    navHostController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                text = character.name,
                color = Black,
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
                        color = Gray,
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
                        color = Gray,
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
                        color = Gray,
                    )
                }
            }
        }
    }
}