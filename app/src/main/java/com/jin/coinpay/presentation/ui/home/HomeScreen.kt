package com.jin.coinpay.presentation.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.jin.coinpay.core.util.toast
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavController) {
    //region handle BackPressed
    val context = LocalContext.current
    var isBack by remember { mutableStateOf(true) }

    LaunchedEffect(isBack) {
        if (!isBack) {
            delay(1000L)
            isBack = true
        }
    }

    BackHandler(enabled = isBack) {
        isBack = false
        context.toast("Press to exit app!")
    }
    //endregion
}