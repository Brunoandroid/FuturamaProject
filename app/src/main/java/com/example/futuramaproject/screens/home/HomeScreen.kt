package com.example.futuramaproject.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.futuramaproject.Screen
import com.example.futuramaproject.components.LoadImageLocal
import com.example.futuramaproject.ui.theme.Black
import com.example.futuramaproject.ui.theme.Dimens
import com.example.futuramaproject.ui.theme.White

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = { AppBar() },
    ) { paddingValues ->
        ContentPage(navHostController, paddingValues)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun ContentPage(navHostController: NavHostController, paddingValues: PaddingValues) {
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
        LoadImageLocal(imageResId = R.drawable.futurama, size = Dimens.SizeXXXLarge, onClick = {
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
            onClick = { navHostController.navigate(Screen.Detail.route) },
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