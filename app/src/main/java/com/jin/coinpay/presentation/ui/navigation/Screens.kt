package com.jin.coinpay.presentation.ui.navigation

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash")
    object WelcomeScreen : Screens("welcome")
    object HomeScreen : Screens("home")
}