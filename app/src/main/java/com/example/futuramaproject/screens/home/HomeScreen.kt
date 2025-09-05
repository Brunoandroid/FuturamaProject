package com.example.futuramaproject.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futuramaproject.R
import com.example.futuramaproject.navigation.Screen
import com.example.futuramaproject.components.FuturamaAppBar
import com.example.futuramaproject.components.LoadImageLocal
import com.example.futuramaproject.screens.home.sections.FullScreenImageDialog
import com.example.futuramaproject.ui.theme.Black
import com.example.futuramaproject.ui.theme.Dimens
import com.example.futuramaproject.ui.theme.White

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = { FuturamaAppBar() },
    ) { paddingValues ->
        ContentPage(
            navHostController,
            paddingValues
        )
    }
}

@Composable
fun ContentPage(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
    val viewModel: HomeViewModel = viewModel()
    val showDialog by viewModel.isShowDialog.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoadImageLocal(
            imageResId = R.drawable.futurama,
            size = Dimens.SizeXXXLarge,
            onClick = {
                viewModel.showDialog()
            })

        Button(
            modifier = Modifier
                .padding(top = Dimens.PaddingXLarge)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Black,
            ),
            shape = RectangleShape,
            onClick = { navHostController.navigate(Screen.Initial.route) }
        ) {
            Text(color = White, text = stringResource(id = R.string.see_characters))
        }

        if (showDialog) {
            FullScreenImageDialog(onDismiss = { viewModel.hideDialog() })
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(navHostController = rememberNavController())
}