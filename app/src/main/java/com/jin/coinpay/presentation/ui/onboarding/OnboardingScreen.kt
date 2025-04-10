package com.jin.coinpay.presentation.ui.onboarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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

        IntroStepIndicator(
            currentStep = uiState.value.step,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 24.dp),
        )

        TextDescription(
            uiState.value.step.descriptionId, modifier = Modifier.weight(2f)
        )

        ButtonNext(onClick = {
            if (uiState.value.step.isLastStep()) {
                //navigate to registration
                navController.navigatePopUpTop(Screens.RegistrationScreen.route)
            } else {
                //next step
                viewModel.nextStep()
            }
        })
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
private fun TextDescription(descriptionId: Int, modifier: Modifier) {
    Text(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        text = stringResource(id = descriptionId),
        style = Typography.headlineLarge,
        fontSize = 24.sp,
        color = MaterialTheme.customColorsPalette.contentPrimary,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun ButtonNext(onClick: () -> Unit) {
    ElevatedButton(
        modifier = Modifier
            .padding(
                bottom = 24.dp, start = 16.dp, end = 16.dp
            )
            .fillMaxWidth(), colors = ButtonDefaults.elevatedButtonColors(
            containerColor = BluePrimary
        ), onClick = {
            onClick()
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
fun IntroStepIndicator(
    currentStep: IntroStep,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFF304FFE),
    inactiveColor: Color = Color(0xFFD0D0D0),
    activeWidth: Int = 37,
    inactiveWidth: Int = 16,
    animationDurationMillis: Int = 300,
    indicatorHeight: Int = 8,
    horizontalPadding: Int = 4,
    indicatorCornerRadius: Int = 4,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = horizontalPadding.dp)
        ) {
            itemsIndexed(IntroStep.entries) { index, step ->
                val isCurrentStep = currentStep == step
                val width by animateDpAsState(
                    targetValue = if (isCurrentStep) activeWidth.dp else inactiveWidth.dp,
                    animationSpec = tween(animationDurationMillis),
                    label = "WidthAnimation"
                )

                val backgroundColor by animateColorAsState(
                    targetValue = if (isCurrentStep) activeColor else inactiveColor,
                    animationSpec = tween(animationDurationMillis),
                    label = "ColorAnimation"
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = horizontalPadding.dp)
                        .width(width)
                        .height(indicatorHeight.dp)
                        .clip(RoundedCornerShape(indicatorCornerRadius.dp))
                        .background(backgroundColor)
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    CoinPayTheme(darkTheme = false) {
        OnboardingScreen(navController = rememberNavController(), viewModel = viewModel())
    }
}