package com.example.stockmarketapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.stockmarketapp.presentation.OnBoardingScreen.OnBoardingScreen
import com.example.stockmarketapp.presentation.company_details.CompanyDetailScreen
import com.example.stockmarketapp.presentation.company_list.CompanyListScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            CompanyListScreen(navController = navController)
        }
        composable(route = Screen.DetailScreen.route + "/{symbol}",
        arguments = listOf(navArgument("symbol"){type = NavType.StringType})
        ){ backStackEntry ->
            backStackEntry.arguments?.getString("symbol")?.let { CompanyDetailScreen(it) }
        }
    }
}