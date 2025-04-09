package com.jin.coinpay.presentation.viewmodel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.jin.coinpay.R
import com.jin.coinpay.presentation.viewmodel.IntroStep.Companion.isLastStep
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class OnBoardingUiState(
    val step: IntroStep = IntroStep.Step1
)

enum class IntroStep(
    @DrawableRes val imageSrcLight: Int,
    @DrawableRes val imageSrcDark: Int,
    @StringRes val descriptionId: Int
) {
    Step1(
        R.drawable.intro1_light, R.drawable.intro1_dark, R.string.txt_onboarding_des_1
    ),
    Step2(
        R.drawable.intro2_light,
        R.drawable.intro2_dark,
        R.string.txt_onboarding_des_2
    ),
    Step3(R.drawable.intro3_light, R.drawable.intro3_dark, R.string.txt_onboarding_des_3);

    companion object {
        fun IntroStep.isLastStep() = this == Step3
        fun fromOrdinal(ordinal: Int) = IntroStep.entries.getOrNull(ordinal) ?: Step1
        fun nextStep(ordinal: Int) = fromOrdinal(ordinal + 1)
    }
}

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {
    private var _uiState: MutableStateFlow<OnBoardingUiState> =
        MutableStateFlow(OnBoardingUiState())

    val uiState: StateFlow<OnBoardingUiState> = _uiState.asStateFlow()

    fun nextStep() {
        val oldStep = _uiState.value.step
        if (oldStep.isLastStep()) {
            return
        }
        val nextStep = IntroStep.nextStep(oldStep.ordinal)
        _uiState.value = _uiState.value.copy(step = nextStep)
    }

    fun getImagSrc(isSystemInDarkTheme: Boolean): Int {
        val currentStep = _uiState.value.step
        return if (isSystemInDarkTheme) {
            currentStep.imageSrcDark
        } else {
            currentStep.imageSrcLight
        }
    }
}