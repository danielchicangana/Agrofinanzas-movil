// screens/cultivos/CultivosViewModel.kt
package com.example.agrofinanzas.screens.cultivos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CultivosViewModel : ViewModel() {

    private val _cultivos = MutableStateFlow<List<Cultivo>>(emptyList())
    val cultivos: StateFlow<List<Cultivo>> = _cultivos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        cargarCultivos()
    }

    fun cargarCultivos() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(800)

            _cultivos.value = listOf(
                Cultivo(
                    nombre = "Aves de Corral",
                    cantidad = "150 unidades",
                    estado = "Producción activa",
                    icono = "pets",
                    color = "#FFA726"
                ),
                Cultivo(
                    nombre = "Ganado Vacuno",
                    cantidad = "45 cabezas",
                    estado = "Producción activa",
                    icono = "pets",
                    color = "#8D6E63"
                ),
                Cultivo(
                    nombre = "Cultivo de Aguacate",
                    cantidad = "2 hectáreas",
                    estado = "En cosecha",
                    icono = "grass",
                    color = "#2E7D32"
                ),
                Cultivo(
                    nombre = "Cultivo de Café",
                    cantidad = "3 hectáreas",
                    estado = "En floración",
                    icono = "grass",
                    color = "#8B4513"
                )
            )
            _isLoading.value = false
        }
    }

    fun agregarCultivo(cultivo: Cultivo) {
        viewModelScope.launch {
            val listaActual = _cultivos.value.toMutableList()
            listaActual.add(cultivo)
            _cultivos.value = listaActual
        }
    }

    fun eliminarCultivo(id: String) {
        // Implementar cuando tengas IDs
    }
}

data class Cultivo(
    val nombre: String,
    val cantidad: String,
    val estado: String,
    val icono: String,
    val color: String
)