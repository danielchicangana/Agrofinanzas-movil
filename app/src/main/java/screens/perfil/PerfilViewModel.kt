// screens/perfil/PerfilViewModel.kt
package com.example.agrofinanzas.screens.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel : ViewModel() {

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    private val _estadisticas = MutableStateFlow(Estadisticas())
    val estadisticas: StateFlow<Estadisticas> = _estadisticas

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isEditing = MutableStateFlow(false)
    val isEditing: StateFlow<Boolean> = _isEditing

    init {
        cargarPerfil()
    }

    fun cargarPerfil() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000)

            _usuario.value = Usuario(
                nombre = "Juan Pérez",
                email = "juan.perez@email.com",
                telefono = "+52 555 123 4567",
                finca = "Finca El Bosque",
                ubicacion = "Veracruz, México",
                avatar = "JP"
            )

            _estadisticas.value = Estadisticas(
                totalCultivos = 12,
                totalVentas = 48,
                aniosExperiencia = 2,
                totalIngresos = 15500.0,
                totalGastos = 8900.0
            )

            _isLoading.value = false
        }
    }

    fun actualizarPerfil(
        nombre: String,
        email: String,
        telefono: String,
        finca: String,
        ubicacion: String
    ) {
        viewModelScope.launch {
            _usuario.value = _usuario.value?.copy(
                nombre = nombre,
                email = email,
                telefono = telefono,
                finca = finca,
                ubicacion = ubicacion
            )
            _isEditing.value = false
        }
    }

    fun toggleEditMode() {
        _isEditing.value = !_isEditing.value
    }

    fun cerrarSesion() {
        viewModelScope.launch {
            // Limpiar datos de sesión
            _usuario.value = null
        }
    }
}

data class Usuario(
    val nombre: String,
    val email: String,
    val telefono: String,
    val finca: String,
    val ubicacion: String,
    val avatar: String
)

data class Estadisticas(
    val totalCultivos: Int = 0,
    val totalVentas: Int = 0,
    val aniosExperiencia: Int = 0,
    val totalIngresos: Double = 0.0,
    val totalGastos: Double = 0.0
)