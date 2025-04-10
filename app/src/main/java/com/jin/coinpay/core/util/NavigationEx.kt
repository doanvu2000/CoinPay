package com.jin.coinpay.core.util

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive = true
    }
}

fun NavController.navigatePopUpTop(route: String, isPopupTop: Boolean = true) {
    val nav = this
    navigate(route) {
        if (isPopupTop) {
            popUpToTop(nav)
        }
    }
}