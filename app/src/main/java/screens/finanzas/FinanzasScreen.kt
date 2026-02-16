package com.example.agrofinanzas.screens.finanzas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun FinanzasScreen(navController: NavController) {

    var concepto by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var selectedTipo by remember { mutableStateOf("Gasto") }
    var selectedCategoria by remember { mutableStateOf("Insumos") }
    var expandedTipo by remember { mutableStateOf(false) }
    var expandedCategoria by remember { mutableStateOf(false) }

    val registros = remember {
        mutableStateListOf(
            RegistroFinanza("Compra de fertilizante", "$ 450.00", "15/03/2024", "Gasto", "Insumos"),
            RegistroFinanza("Venta de maíz", "$ 1,200.00", "14/03/2024", "Ingreso", "Ventas"),
            RegistroFinanza("Mantenimiento tractor", "$ 200.00", "13/03/2024", "Gasto", "Maquinaria"),
            RegistroFinanza("Venta de frijol", "$ 850.00", "12/03/2024", "Ingreso", "Ventas"),
            RegistroFinanza("Compra de semillas", "$ 300.00", "11/03/2024", "Gasto", "Insumos"),
            RegistroFinanza("Pago de jornaleros", "$ 600.00", "10/03/2024", "Gasto", "Personal"),
            RegistroFinanza("Venta de café", "$ 1,500.00", "09/03/2024", "Ingreso", "Ventas")
        )
    }

    val opcionesTipo = listOf("Gasto", "Ingreso")
    val opcionesCategoria = listOf("Insumos", "Maquinaria", "Ventas", "Personal", "Mantenimiento", "Otros")

    val verdeAgro = Color(0xFF18D92E)

    // Usamos Column en lugar de Scaffold para respetar el padding del MainActivity
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // TopAppBar personalizado
        TopAppBar(
            title = {
                Text(
                    "Registro de Finanzas",
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
                    Icon(Icons.Default.FilterList, contentDescription = "Filtrar", tint = verdeAgro)
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Download, contentDescription = "Exportar", tint = verdeAgro)
                }
            }
        )

        // LazyColumn con el contenido principal
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // TARJETA DE RESUMEN
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1A1A1A)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Balance
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Balance", fontSize = 12.sp, color = Color.Gray)
                            Text("$ 5,100", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = verdeAgro)
                        }
                        // Ingresos
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Ingresos", fontSize = 12.sp, color = Color.Gray)
                            Text("$ 3,550", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = verdeAgro)
                        }
                        // Gastos
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Gastos", fontSize = 12.sp, color = Color.Gray)
                            Text("$ 1,550", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFFEF5350))
                        }
                    }
                }
            }

            // FORMULARIO PARA NUEVO REGISTRO
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
                            text = "Nuevo Registro",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = verdeAgro
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // CAMPO CONCEPTO
                        OutlinedTextField(
                            value = concepto,
                            onValueChange = { concepto = it },
                            label = { Text("Concepto", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Description,
                                    contentDescription = null,
                                    tint = verdeAgro
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // CAMPO MONTO
                        OutlinedTextField(
                            value = monto,
                            onValueChange = { monto = it },
                            label = { Text("Monto", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.AttachMoney,
                                    contentDescription = null,
                                    tint = verdeAgro
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // CAMPO FECHA
                        OutlinedTextField(
                            value = fecha,
                            onValueChange = { fecha = it },
                            label = { Text("Fecha (DD/MM/AAAA)", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = null,
                                    tint = verdeAgro
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // SELECTOR TIPO
                        ExposedDropdownMenuBox(
                            expanded = expandedTipo,
                            onExpandedChange = { expandedTipo = it }
                        ) {
                            OutlinedTextField(
                                value = selectedTipo,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Tipo", color = Color.White) },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTipo) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor()
                            )
                            ExposedDropdownMenu(
                                expanded = expandedTipo,
                                onDismissRequest = { expandedTipo = false },
                                containerColor = Color(0xFF1A1A1A)
                            ) {
                                opcionesTipo.forEach { opcion ->
                                    DropdownMenuItem(
                                        text = { Text(opcion, color = Color.White) },
                                        onClick = {
                                            selectedTipo = opcion
                                            expandedTipo = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // SELECTOR CATEGORÍA
                        ExposedDropdownMenuBox(
                            expanded = expandedCategoria,
                            onExpandedChange = { expandedCategoria = it }
                        ) {
                            OutlinedTextField(
                                value = selectedCategoria,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Categoría", color = Color.White) },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategoria) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor()
                            )
                            ExposedDropdownMenu(
                                expanded = expandedCategoria,
                                onDismissRequest = { expandedCategoria = false },
                                containerColor = Color(0xFF1A1A1A)
                            ) {
                                opcionesCategoria.forEach { opcion ->
                                    DropdownMenuItem(
                                        text = { Text(opcion, color = Color.White) },
                                        onClick = {
                                            selectedCategoria = opcion
                                            expandedCategoria = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // BOTÓN GUARDAR
                        Button(
                            onClick = {
                                // Solo visual - no hace nada
                                println("Botón Guardar presionado")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = verdeAgro,
                                contentColor = Color.Black
                            )
                        ) {
                            Icon(
                                Icons.Default.Save,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Guardar Registro")
                        }
                    }
                }
            }

            // LISTA DE REGISTROS
            item {
                Text(
                    text = "Registros Recientes",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(registros) { registro ->
                RegistroItem(registro, verdeAgro)
            }

            // Espacio adicional al final para que no se pegue al BottomBar
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun RegistroItem(registro: RegistroFinanza, verdeAgro: Color) {
    val esGasto = registro.tipo == "Gasto"

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1A1A)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Icono según categoría
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (esGasto) Color(0xFF331111) else Color(0xFF113311)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = when (registro.categoria) {
                            "Insumos" -> Icons.Default.Grass
                            "Maquinaria" -> Icons.Default.Build
                            "Ventas" -> Icons.Default.AttachMoney
                            "Personal" -> Icons.Default.Person
                            else -> Icons.Default.Receipt
                        },
                        contentDescription = null,
                        tint = if (esGasto) Color(0xFFEF5350) else verdeAgro
                    )
                }

                Column {
                    Text(
                        text = registro.concepto,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Text(
                        text = "${registro.fecha} • ${registro.categoria}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = registro.monto,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (esGasto) Color(0xFFEF5350) else verdeAgro
                )
                Text(
                    text = registro.tipo,
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

data class RegistroFinanza(
    val concepto: String,
    val monto: String,
    val fecha: String,
    val tipo: String,
    val categoria: String
)