package com.jin.coinpay.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RegisterStep() {
    object FirstScreen : RegisterStep()
    object SecondStep : RegisterStep()
}

data class RegistrationUiState(
    val step: RegisterStep = RegisterStep.FirstScreen
)

@HiltViewModel
class RegistrationViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState = _uiState.asStateFlow()

    fun updateUiState(state: RegisterStep) = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(step = state)
    }
}