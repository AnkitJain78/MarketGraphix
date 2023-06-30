package com.example.stockmarketapp.presentation.OnBoardingScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarketapp.data.repository.StockRepositoryImpl
import com.example.stockmarketapp.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val repository: StockRepositoryImpl
): ViewModel() {
    private val stDestination: MutableState<String> = mutableStateOf(Screen.SplashScreen.route)
    val startDestination: State<String> = stDestination
    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveOnBoardingState(completed)
        }
    }
}