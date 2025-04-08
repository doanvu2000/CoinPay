package com.jin.coinpay.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.jin.coinpay.core.base.theme.CoinPayTheme
import com.jin.coinpay.presentation.ui.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinPayTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets.Companion.safeDrawing,
                    modifier = Modifier.Companion.fillMaxSize()
                ) { innerPadding ->
                    Column(modifier = Modifier.Companion.padding(innerPadding)) {
                        Navigation()
                    }
                }
            }
        }
    }
}