package com.jin.coinpay.presentation.ui.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jin.coinpay.core.util.navigatePopUpTop
import com.jin.coinpay.presentation.ui.navigation.Screen

@Composable
fun WelcomeScreen(navController: NavController) {
    Column {
        Text("Welcome")
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedButton(onClick = {
            navController.navigatePopUpTop(Screen.HomeScreen.route, isPopupTop = true)
        }) {
            Text("Next")
        }
    }
}