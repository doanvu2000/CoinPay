package com.jin.coinpay.presentation.ui.registration

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.jin.coinpay.core.base.theme.backgroundColor
import com.jin.coinpay.presentation.viewmodel.RegisterStep
import com.jin.coinpay.presentation.viewmodel.RegistrationViewModel

@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val progress by animateFloatAsState(0.1f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        LinearProgressIndicator(
            progress = {
                progress
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        when (uiState.value.step) {
            RegisterStep.FirstScreen -> {
                WelcomeRegisterScreen(onClickSignUp = {
                    //show create account layout
                }, onClickLogin = {
                    //show login layout
                })
            }

            RegisterStep.SecondStep -> {

            }
        }
    }
}