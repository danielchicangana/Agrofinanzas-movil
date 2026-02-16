// screens/comentarios/ComentariosScreen.kt
package com.example.agrofinanzas.screens.comentarios

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComentariosScreen(navController: NavController) {

    // Estados para el formulario
    var comentario by remember { mutableStateOf("") }
    var calificacion by remember { mutableStateOf(0) }

    // Lista de comentarios de ejemplo
    val comentarios = remember {
        mutableStateListOf(
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
            ),
            Comentario(
                usuario = "Roberto Sánchez",
                fecha = "11/03/2024",
                texto = "La uso diario para mi finca. Me ha ayudado a organizar mejor mis finanzas.",
                calificacion = 5,
                avatar = "RS"
            )
        )
    }

    val verdeAgro = Color(0xFF18D92E)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // TopAppBar
        TopAppBar(
            title = {
                Text(
                    "Comentarios",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black,
                titleContentColor = Color.White
            ),
            actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Search, contentDescription = "Buscar", tint = verdeAgro)
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // TARJETA DE ESTADÍSTICAS
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1A1A1A)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Calificación General",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "4.8",
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                color = verdeAgro
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                // Estrellas
                                Row {
                                    repeat(5) { index ->
                                        Icon(
                                            imageVector = if (index < 4) Icons.Default.Star else Icons.Default.StarHalf,
                                            contentDescription = null,
                                            tint = Color(0xFFFFC107),
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "Basado en 24 comentarios",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }

            // FORMULARIO PARA NUEVO COMENTARIO
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1A1A1A)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Deja tu comentario",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = verdeAgro
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Calificación con estrellas
                        Text(
                            text = "Tu calificación:",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(5) { index ->
                                IconButton(
                                    onClick = { calificacion = index + 1 }
                                ) {
                                    Icon(
                                        imageVector = if (index < calificacion) Icons.Default.Star else Icons.Default.StarBorder,
                                        contentDescription = null,
                                        tint = Color(0xFFFFC107),
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Campo de comentario
                        OutlinedTextField(
                            value = comentario,
                            onValueChange = { comentario = it },
                            label = { Text("Escribe tu comentario...", color = Color.Gray) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Chat,
                                    contentDescription = null,
                                    tint = verdeAgro
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Botón Publicar
                        Button(
                            onClick = {
                                if (comentario.isNotBlank() && calificacion > 0) {
                                    // Solo visual - no guarda realmente
                                    println("Comentario publicado: $comentario")
                                    comentario = ""
                                    calificacion = 0
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = verdeAgro,
                                contentColor = Color.Black
                            ),
                            enabled = comentario.isNotBlank() && calificacion > 0
                        ) {
                            Icon(Icons.Default.Send, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Publicar Comentario")
                        }
                    }
                }
            }

            // LISTA DE COMENTARIOS
            item {
                Text(
                    text = "Comentarios Recientes",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(comentarios) { comentario ->
                ComentarioItem(comentario, verdeAgro)
            }

            // Espacio adicional al final
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ComentarioItem(comentario: Comentario, verdeAgro: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1A1A)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar con iniciales
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(verdeAgro.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = comentario.avatar,
                        fontWeight = FontWeight.Bold,
                        color = verdeAgro,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = comentario.usuario,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Text(
                        text = comentario.fecha,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Estrellas de calificación
            Row {
                repeat(5) { index ->
                    Icon(
                        imageVector = if (index < comentario.calificacion) Icons.Default.Star else Icons.Default.StarBorder,
                        contentDescription = null,
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Texto del comentario
            Text(
                text = comentario.texto,
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = {}) {
                    Icon(Icons.Default.ThumbUp, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Útil", color = Color.Gray, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))

                TextButton(onClick = {}) {
                    Icon(Icons.Default.Reply, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Responder", color = Color.Gray, fontSize = 12.sp)
                }
            }
        }
    }
}

data class Comentario(
    val usuario: String,
    val fecha: String,
    val texto: String,
    val calificacion: Int,
    val avatar: String
)