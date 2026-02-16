// screens/finanzas/FinanzasViewModel.kt
package com.example.agrofinanzas.screens.finanzas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FinanzasViewModel : ViewModel() {

    private val _registros = MutableStateFlow<List<RegistroFinanza>>(emptyList())
    val registros: StateFlow<List<RegistroFinanza>> = _registros

    private val _balanceTotal = MutableStateFlow(0.0)
    val balanceTotal: StateFlow<Double> = _balanceTotal

    private val _ingresosMes = MutableStateFlow(0.0)
    val ingresosMes: StateFlow<Double> = _ingresosMes

    private val _gastosMes = MutableStateFlow(0.0)
    val gastosMes: StateFlow<Double> = _gastosMes

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        cargarDatosFinancieros()
    }

    fun cargarDatosFinancieros() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000)

            _registros.value = listOf(
                RegistroFinanza(
                    concepto = "Compra de fertilizante",
                    monto = 450.00,
                    fecha = "15/03/2024",
                    tipo = "Gasto",
                    categoria = "Insumos"
                ),
                RegistroFinanza(
                    concepto = "Venta de maíz",
                    monto = 1200.00,
                    fecha = "14/03/2024",
                    tipo = "Ingreso",
                    categoria = "Ventas"
                ),
                RegistroFinanza(
                    concepto = "Mantenimiento tractor",
                    monto = 200.00,
                    fecha = "13/03/2024",
                    tipo = "Gasto",
                    categoria = "Maquinaria"
                ),
                RegistroFinanza(
                    concepto = "Venta de frijol",
                    monto = 850.00,
                    fecha = "12/03/2024",
                    tipo = "Ingreso",
                    categoria = "Ventas"
                )
            )

            calcularResumen()
            _isLoading.value = false
        }
    }

    private fun calcularResumen() {
        var ingresos = 0.0
        var gastos = 0.0

        _registros.value.forEach { registro ->
            if (registro.tipo == "Ingreso") {
                ingresos += registro.monto
            } else {
                gastos += registro.monto
            }
        }

        _ingresosMes.value = ingresos
        _gastosMes.value = gastos
        _balanceTotal.value = ingresos - gastos
    }

    fun agregarRegistro(registro: RegistroFinanza) {
        viewModelScope.launch {
            val listaActual = _registros.value.toMutableList()
            listaActual.add(0, registro) // Agregar al inicio
            _registros.value = listaActual
            calcularResumen()
        }
    }
}

data class RegistroFinanza(
    val concepto: String,
    val monto: Double,
    val fecha: String,
    val tipo: String,
    val categoria: String
)