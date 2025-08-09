package com.kun.portfolio.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kun.portfolio.ui.main.MainPage

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = PageRouter.Main.router
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = PageRouter.Main.router) {
            MainPage()
        }
    }
}