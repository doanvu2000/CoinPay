package com.jin.coinpay.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jin.coinpay.presentation.ui.home.HomeScreen
import com.jin.coinpay.presentation.ui.onboarding.OnboardingScreen
import com.jin.coinpay.presentation.ui.splash.SplashScreen
import com.jin.coinpay.presentation.ui.welcome.WelcomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screens.OnboardingScreen.route) {
            OnboardingScreen(navController = navController)
        }
        composable(Screens.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

    }
}