package com.jin.coinpay.presentation.ui.navigation

sealed class Screen(val route: String) {
    object WelcomeScreen : Screen("welcome")
    object HomeScreen : Screen("home")
}