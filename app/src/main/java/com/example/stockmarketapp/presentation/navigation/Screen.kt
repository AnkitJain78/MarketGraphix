package com.example.stockmarketapp.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object OnBoarding : Screen(route = "boarding_screen")
    object DetailScreen : Screen(route = "detail_screen")
    object SplashScreen : Screen(route = "splash_screen")
}
