// screens/comentarios/ComentariosViewModel.kt
package com.example.agrofinanzas.screens.comentarios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ComentariosViewModel : ViewModel() {

    private val _comentarios = MutableStateFlow<List<Comentario>>(emptyList())
    val comentarios: StateFlow<List<Comentario>> = _comentarios

    private val _calificacionPromedio = MutableStateFlow(0.0)
    val calificacionPromedio: StateFlow<Double> = _calificacionPromedio

    private val _totalComentarios = MutableStateFlow(0)
    val totalComentarios: StateFlow<Int> = _totalComentarios

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        cargarComentarios()
    }

    fun cargarComentarios() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(800)

            _comentarios.value = listOf(
                Comentario(
                    usuario = "Juan Pérez",
                    fecha = "15/03/2024",
                    texto = "Excelente app para llevar el control de mis cultivos. Muy recomendada!",
                    calificacion = 5,
                    avatar = "JP"
                ),
                Comentario(
                    usuario = "María García",
                    fecha = "14/03/2024",
                    texto = "Me encanta la sección de finanzas, puedo ver todos mis gastos e ingresos fácilmente.",
                    calificacion = 5,
                    avatar = "MG"
                ),
                Comentario(
                    usuario = "Carlos López",
                    fecha = "13/03/2024",
                    texto = "Buenas herramientas, aunque me gustaría que agregaran más opciones de cultivos.",
                    calificacion = 4,
                    avatar = "CL"
                ),
                Comentario(
                    usuario = "Ana Martínez",
                    fecha = "12/03/2024",
                    texto = "Muy intuitiva y fácil de usar. El diseño es muy moderno.",
                    calificacion = 5,
                    avatar = "AM"
                )
            )

            calcularPromedio()
            _isLoading.value = false
        }
    }

    private fun calcularPromedio() {
        val total = _comentarios.value.size
        val suma = _comentarios.value.sumOf { it.calificacion }

        _totalComentarios.value = total
        _calificacionPromedio.value = if (total > 0) suma.toDouble() / total else 0.0
    }

    fun agregarComentario(usuario: String, texto: String, calificacion: Int) {
        viewModelScope.launch {
            val nuevoComentario = Comentario(
                usuario = usuario,
                fecha = obtenerFechaActual(),
                texto = texto,
                calificacion = calificacion,
                avatar = obtenerIniciales(usuario)
            )

            val listaActual = _comentarios.value.toMutableList()
            listaActual.add(0, nuevoComentario)
            _comentarios.value = listaActual
            calcularPromedio()
        }
    }

    private fun obtenerFechaActual(): String {
        // Implementar formato de fecha
        return "16/03/2024"
    }

    private fun obtenerIniciales(nombre: String): String {
        return nombre.split(" ")
            .take(2)
            .map { it.firstOrNull() ?: "" }
            .joinToString("")
            .uppercase()
    }
}

data class Comentario(
    val usuario: String,
    val fecha: String,
    val texto: String,
    val calificacion: Int,
    val avatar: String
)