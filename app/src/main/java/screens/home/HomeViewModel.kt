package com.example.agrofinanzas.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.agrofinanzas.R

data class Noticia(
    val titulo: String,
    val descripcion: String,
    val imagen: Int
)

class HomeViewModel : ViewModel() {

    private val _noticias = MutableStateFlow(
        listOf(
            Noticia(
                "Cuidado de tus vacas",
                "Aprende a mejorar la producción y la salud de tu ganado.",
                R.drawable.vacas
            ),
            Noticia(
                "Cómo cuidar tus cultivos",
                "Técnicas modernas para aumentar la productividad.",
                R.drawable.cultivos
            ),
            Noticia(
                "Mejor manejo de finanzas",
                "Organiza tus ingresos y gastos de forma inteligente.",
                R.drawable.finanzas
            )
        )
    )

    val noticias = _noticias.asStateFlow()
}
