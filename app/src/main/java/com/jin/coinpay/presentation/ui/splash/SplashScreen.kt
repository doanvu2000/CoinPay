package com.jin.coinpay.presentation.ui.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.jin.coinpay.R
import com.jin.coinpay.core.util.navigatePopUpTop
import com.jin.coinpay.presentation.ui.navigation.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

var NEXT_SCREEN = Screens.OnboardingScreen.route

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000L)
        withContext(Dispatchers.Main) {
            navController.navigatePopUpTop(NEXT_SCREEN, true)
        }
    }

    Box {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = R.drawable.splash_background,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(40.dp), color = Color.White
            )

            Text("This action can be contain advertisement.", color = Color.White)
        }

    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SplashScreenPreview() {
//    SplashScreen(navController = NavController(androidx.compose.ui.platform.LocalContext.current))
//}