package com.jin.coinpay.presentation.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.jin.coinpay.R
import com.jin.coinpay.core.base.theme.BluePrimary
import com.jin.coinpay.core.base.theme.CoinPayTheme
import com.jin.coinpay.core.base.theme.Typography
import com.jin.coinpay.core.base.theme.customColorsPalette
import com.jin.coinpay.core.util.navigatePopUpTop
import com.jin.coinpay.presentation.ui.navigation.Screens
import com.jin.coinpay.presentation.viewmodel.IntroStep
import com.jin.coinpay.presentation.viewmodel.IntroStep.Companion.isLastStep
import com.jin.coinpay.presentation.viewmodel.OnBoardingUiState
import com.jin.coinpay.presentation.viewmodel.OnBoardingViewModel

@Composable
fun OnboardingScreen(navController: NavController, viewModel: OnBoardingViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.customColorsPalette.backgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageSrc = viewModel.getImagSrc(isSystemInDarkTheme())
        ImageIntro(imageSrc)

        Spacer(Modifier.height(24.dp))

        IndicatorStep(Modifier.weight(1f), uiState.value.step)

        Spacer(Modifier.height(24.dp))

        TextDescription(
            uiState, modifier = Modifier.weight(2f)
        )

        ButtonNext(uiState.value.step, navController, viewModel)
    }
}

@Composable
private fun ImageIntro(imagSrc: Int) {
    AsyncImage(
        model = imagSrc,
        contentDescription = null,
        contentScale = ContentScale.Inside,
        modifier = Modifier
            .size(220.dp, 320.dp)
            .padding(top = 48.dp)
    )
}

@Composable
private fun TextDescription(uiState: State<OnBoardingUiState>, modifier: Modifier) {
    Text(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        text = stringResource(id = uiState.value.step.descriptionId),
        style = Typography.headlineLarge,
        fontSize = 24.sp,
        color = MaterialTheme.customColorsPalette.contentPrimary,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun ButtonNext(
    currentStep: IntroStep, navController: NavController, viewModel: OnBoardingViewModel
) {
    ElevatedButton(
        modifier = Modifier
            .padding(
                bottom = 24.dp, start = 16.dp, end = 16.dp
            )
            .fillMaxWidth(), colors = ButtonDefaults.elevatedButtonColors(
            containerColor = BluePrimary
        ), onClick = {
            if (currentStep.isLastStep()) {
                //navigate to registration
                navController.navigatePopUpTop(Screens.RegistrationScreen.route)
            } else {
                //next step
                viewModel.nextStep()
            }
        }) {
        Text(
            text = stringResource(R.string.txt_next),
            style = Typography.headlineMedium,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun IndicatorStep(modifier: Modifier, currentStep: IntroStep) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        List(IntroStep.entries.size) { index ->
            val isCurrentStep = currentStep.ordinal == index
            val indicatorSize = if (isCurrentStep) {
                DpSize(16.dp, 8.dp)
            } else {
                DpSize(37.dp, 8.dp)
            }
            val backgroundColor = if (isCurrentStep) {
                Color(0xFF304FFE)
            } else {
                Color(0xFFD0D0D0)
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(indicatorSize)
                    .clip(RoundedCornerShape(4.dp))
                    .background(backgroundColor)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    val navController = rememberNavController()
    CoinPayTheme(darkTheme = false) {
        OnboardingScreen(navController = navController, viewModel = viewModel())
    }
}