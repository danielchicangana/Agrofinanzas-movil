package com.example.agrofinanzas.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onRememberChange(value: Boolean) {
        _uiState.update { it.copy(rememberMe = value) }
    }

    fun canContinue(): Boolean {
        val state = _uiState.value
        return state.email.isNotBlank() && state.password.isNotBlank()
    }
}
