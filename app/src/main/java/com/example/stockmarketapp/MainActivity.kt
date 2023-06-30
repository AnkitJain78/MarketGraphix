package com.example.stockmarketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.stockmarketapp.presentation.splashScreen.SplashViewModel
import com.example.stockmarketapp.presentation.navigation.SetupNavGraph
import com.example.stockmarketapp.ui.theme.StockMarketAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketAppTheme() {
                val navController = rememberNavController()
                val screen by splashViewModel.startDestination
                SetupNavGraph(navController = navController,
                    startDestination = screen)
                }
            }
        }
    }