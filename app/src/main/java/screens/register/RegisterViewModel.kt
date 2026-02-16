package com.example.agrofinanzas.screens.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel : ViewModel() {

    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre.asStateFlow()

    private val _correo = MutableStateFlow("")
    val correo: StateFlow<String> = _correo.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun onNombreChange(value: String) {
        _nombre.value = value
    }

    fun onCorreoChange(value: String) {
        _correo.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun register(): Boolean {
        return _nombre.value.isNotBlank() &&
                _correo.value.isNotBlank() &&
                _password.value.length >= 6
    }
}
