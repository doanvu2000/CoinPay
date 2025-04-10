package com.jin.coinpay.presentation.viewmodel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.jin.coinpay.R
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
        val stepCount
            get() = entries.size
    }
}

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {
    private var _uiState: MutableStateFlow<OnBoardingUiState> =
        MutableStateFlow(OnBoardingUiState())

    val uiState: StateFlow<OnBoardingUiState> = _uiState.asStateFlow()

    fun updateStep(pageIndex: Int) {
        val step = IntroStep.fromOrdinal(pageIndex)
        _uiState.value = _uiState.value.copy(step = step)
    }

    fun getImagSrc(currentStep: Int, isSystemInDarkTheme: Boolean): Int {
        val step = IntroStep.fromOrdinal(currentStep)
        return if (isSystemInDarkTheme) {
            step.imageSrcDark
        } else {
            step.imageSrcLight
        }
    }
}