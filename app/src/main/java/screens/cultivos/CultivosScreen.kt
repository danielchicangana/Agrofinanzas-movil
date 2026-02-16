package com.example.agrofinanzas.screens.cultivos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CultivosScreen(navController: NavController) {

    // ❌ QUITAMOS EL SCAFFOLD y el BOTTOMBAR de aquí
    // Usamos solo una Columna con el gradiente
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color.Black, Color(0xFF0F2E16))
                )
            )
            .padding(20.dp)
    ) {
        Text(
            text = "Producción Agropecuaria",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF18D92E)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Aquí se ven UNA SOLA VEZ (no duplicados)
        AgroCard("Aves de Corral")
        AgroCard("Ganado Vacuno")
        AgroCard("Cultivo de Aguacate")
        AgroCard("Cultivo de Café")

        // Espacio al final para que no se pegue al BottomBar
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun AgroCard(titulo: String) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1B5E20)
        )
    ) {
        Box(
            modifier = Modifier
                .height(120.dp)
                .padding(20.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = titulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}