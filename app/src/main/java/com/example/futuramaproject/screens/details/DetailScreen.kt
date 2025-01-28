package com.example.futuramaproject.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futuramaproject.Screen

@Composable
fun DetailScreen(navHostController: NavHostController) {
    val viewModel: DetailViewModel = hiltViewModel()
    val items by viewModel.items.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(true)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                navHostController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            },
            text = if (isLoading) "Carregando. . ." else items ?: "Nenhum dado retornado",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreview() {
    DetailScreen(navHostController = rememberNavController())
}