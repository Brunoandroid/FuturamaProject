package com.example.futuramaproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.futuramaproject.navigation.Screen
import com.example.futuramaproject.screens.detail.DetailsScreen
import com.example.futuramaproject.screens.home.HomeScreen
import com.example.futuramaproject.screens.initial.InitialScreen

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
            route = Screen.Initial.route
        ) {
            InitialScreen(navHostController = navHostController)
        }
        composable(
            route = Screen.Detail.route
        ) {
            DetailsScreen()
        }
    }
}