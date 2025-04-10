package com.jin.coinpay.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jin.coinpay.core.util.enterTransition
import com.jin.coinpay.core.util.exitTransition
import com.jin.coinpay.presentation.ui.home.HomeScreen
import com.jin.coinpay.presentation.ui.onboarding.OnboardingScreen
import com.jin.coinpay.presentation.ui.registration.RegistrationScreen
import com.jin.coinpay.presentation.ui.splash.SplashScreen
import com.jin.coinpay.presentation.ui.welcome.WelcomeScreen
import com.jin.coinpay.presentation.viewmodel.OnBoardingViewModel
import com.jin.coinpay.presentation.viewmodel.RegistrationViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val startDestination = Screens.SplashScreen.route
    val onBoardingViewModel: OnBoardingViewModel = viewModel<OnBoardingViewModel>()
    val registrationViewModel: RegistrationViewModel = viewModel<RegistrationViewModel>()

    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screens.OnboardingScreen.route) {
            OnboardingScreen(navController = navController, viewModel = onBoardingViewModel)
        }
        composable(
            Screens.RegistrationScreen.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition
        ) {
            RegistrationScreen(navController = navController, viewModel = registrationViewModel)
        }
        composable(Screens.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
    }
}