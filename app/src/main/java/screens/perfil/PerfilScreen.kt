package com.example.agrofinanzas.screens.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(navController: NavController) {

    // Estados para los datos del perfil
    var nombre by remember { mutableStateOf("Juan Pérez") }
    var email by remember { mutableStateOf("juan.perez@email.com") }
    var telefono by remember { mutableStateOf("+52 555 123 4567") }
    var finca by remember { mutableStateOf("Finca El Bosque") }
    var ubicacion by remember { mutableStateOf("Veracruz, México") }
    var editMode by remember { mutableStateOf(false) }

    // Variables temporales para edición
    var tempNombre by remember { mutableStateOf(nombre) }
    var tempEmail by remember { mutableStateOf(email) }
    var tempTelefono by remember { mutableStateOf(telefono) }
    var tempFinca by remember { mutableStateOf(finca) }
    var tempUbicacion by remember { mutableStateOf(ubicacion) }

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
                    "Mi Perfil",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black,
                titleContentColor = Color.White
            ),
            actions = {
                if (!editMode) {
                    // Botón Editar
                    IconButton(onClick = {
                        editMode = true
                        tempNombre = nombre
                        tempEmail = email
                        tempTelefono = telefono
                        tempFinca = finca
                        tempUbicacion = ubicacion
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar", tint = verdeAgro)
                    }
                } else {
                    // Botón Guardar
                    IconButton(onClick = {
                        nombre = tempNombre
                        email = tempEmail
                        telefono = tempTelefono
                        finca = tempFinca
                        ubicacion = tempUbicacion
                        editMode = false
                    }) {
                        Icon(Icons.Default.Save, contentDescription = "Guardar", tint = verdeAgro)
                    }
                    // Botón Cancelar
                    IconButton(onClick = {
                        editMode = false
                    }) {
                        Icon(Icons.Default.Close, contentDescription = "Cancelar", tint = Color.Red)
                    }
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

            // TARJETA DE FOTO DE PERFIL
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
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Avatar grande
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(verdeAgro.copy(alpha = 0.2f))
                                .border(3.dp, verdeAgro, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "JP",
                                fontWeight = FontWeight.Bold,
                                color = verdeAgro,
                                fontSize = 40.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Nombre del usuario
                        Text(
                            text = if (editMode) tempNombre else nombre,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 24.sp
                        )

                        Text(
                            text = if (editMode) tempEmail else email,
                            color = Color.Gray,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Badge de agricultor
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = verdeAgro.copy(alpha = 0.2f),
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(
                                text = "🌱 Agricultor Verificado",
                                color = verdeAgro,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            // TARJETA DE INFORMACIÓN PERSONAL
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
                            text = "Información Personal",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = verdeAgro
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        if (editMode) {
                            // MODO EDICIÓN
                            OutlinedTextField(
                                value = tempNombre,
                                onValueChange = { tempNombre = it },
                                label = { Text("Nombre completo", color = Color.Gray) },
                                modifier = Modifier.fillMaxWidth(),
                                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = verdeAgro) }
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = tempEmail,
                                onValueChange = { tempEmail = it },
                                label = { Text("Correo electrónico", color = Color.Gray) },
                                modifier = Modifier.fillMaxWidth(),
                                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = verdeAgro) }
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = tempTelefono,
                                onValueChange = { tempTelefono = it },
                                label = { Text("Teléfono", color = Color.Gray) },
                                modifier = Modifier.fillMaxWidth(),
                                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = verdeAgro) }
                            )
                        } else {
                            // MODO VISUALIZACIÓN
                            InfoRow(
                                iconVector = Icons.Default.Person,
                                label = "Nombre",
                                value = nombre,
                                verdeAgro = verdeAgro
                            )
                            Divider(color = Color.DarkGray, modifier = Modifier.padding(vertical = 8.dp))

                            InfoRow(
                                iconVector = Icons.Default.Email,
                                label = "Email",
                                value = email,
                                verdeAgro = verdeAgro
                            )
                            Divider(color = Color.DarkGray, modifier = Modifier.padding(vertical = 8.dp))

                            InfoRow(
                                iconVector = Icons.Default.Phone,
                                label = "Teléfono",
                                value = telefono,
                                verdeAgro = verdeAgro
                            )
                        }
                    }
                }
            }

            // TARJETA DE INFORMACIÓN DE LA FINCA
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
                            text = "Mi Finca",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = verdeAgro
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        if (editMode) {
                            OutlinedTextField(
                                value = tempFinca,
                                onValueChange = { tempFinca = it },
                                label = { Text("Nombre de la finca", color = Color.Gray) },
                                modifier = Modifier.fillMaxWidth(),
                                leadingIcon = { Icon(Icons.Default.Home, contentDescription = null, tint = verdeAgro) }
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = tempUbicacion,
                                onValueChange = { tempUbicacion = it },
                                label = { Text("Ubicación", color = Color.Gray) },
                                modifier = Modifier.fillMaxWidth(),
                                leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = verdeAgro) }
                            )
                        } else {
                            InfoRow(
                                iconVector = Icons.Default.Home,
                                label = "Finca",
                                value = finca,
                                verdeAgro = verdeAgro
                            )
                            Divider(color = Color.DarkGray, modifier = Modifier.padding(vertical = 8.dp))

                            InfoRow(
                                iconVector = Icons.Default.LocationOn,
                                label = "Ubicación",
                                value = ubicacion,
                                verdeAgro = verdeAgro
                            )
                        }
                    }
                }
            }

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
                            text = "Estadísticas",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = verdeAgro
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            StatItem(
                                valor = "12",
                                label = "Cultivos",
                                iconVector = Icons.Default.Grass,
                                verdeAgro = verdeAgro
                            )

                            StatItem(
                                valor = "48",
                                label = "Ventas",
                                iconVector = Icons.Default.AttachMoney,
                                verdeAgro = verdeAgro
                            )

                            StatItem(
                                valor = "2",
                                label = "Años",
                                iconVector = Icons.Default.CalendarToday,
                                verdeAgro = verdeAgro
                            )
                        }
                    }
                }
            }

            // BOTÓN DE CERRAR SESIÓN
            item {
                Button(
                    onClick = {
                        // Volver al login
                        navController.navigate("screens/login") {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF331111),
                        contentColor = Color(0xFFEF5350)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Logout, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cerrar Sesión")
                }
            }

            // Espacio adicional
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun InfoRow(
    iconVector: ImageVector,  // 👈 CAMBIADO de "icon" a "iconVector"
    label: String,
    value: String,
    verdeAgro: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = iconVector,
            contentDescription = null,
            tint = verdeAgro,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = value,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun StatItem(
    valor: String,
    label: String,
    iconVector: ImageVector,  // 👈 CAMBIADO de "icon" a "iconVector"
    verdeAgro: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = iconVector,
            contentDescription = null,
            tint = verdeAgro,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = valor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}