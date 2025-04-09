package com.jin.coinpay.presentation.ui.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun RegistrationScreen(navController: NavController) {
    val progress by remember {
        mutableStateOf(0.1f)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LinearProgressIndicator(progress = {
            progress
        }, modifier = Modifier.fillMaxWidth())
    }
}