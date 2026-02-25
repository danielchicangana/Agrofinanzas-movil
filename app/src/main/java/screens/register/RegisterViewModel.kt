package com.example.agrofinanzas.screens.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class RegisterUiState(
    val nombre: String = "",
    val correo: String = "",
    val password: String = "",
    val confirmarPassword: String = ""
)

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNombreChange(value: String) {
        _uiState.update { it.copy(nombre = value) }
    }

    fun onCorreoChange(value: String) {
        _uiState.update { it.copy(correo = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onConfirmarPasswordChange(value: String) {
        _uiState.update { it.copy(confirmarPassword = value) }
    }

    fun canContinue(): Boolean {
        val state = _uiState.value
        return state.nombre.isNotBlank() &&
            state.correo.isNotBlank() &&
            state.password.isNotBlank() &&
            state.password == state.confirmarPassword
    }
}
