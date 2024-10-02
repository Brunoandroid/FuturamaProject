package com.example.futuramaproject

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.futuramaproject.screens.details.DetailScreen
import com.example.futuramaproject.screens.home.HomeScreen

@Composable
fun SetupNavGraph() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navHostController = navHostController)
        }
        composable(
            route = Screen.Detail.route
        ) {
            DetailScreen(navHostController = navHostController)
        }
    }
}