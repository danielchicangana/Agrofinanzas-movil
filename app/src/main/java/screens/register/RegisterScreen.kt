package com.example.agrofinanzas.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.agrofinanzas.R
import com.example.agrofinanzas.screens.AppRoutes

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.radialGradient(colors = listOf(Color(0xFF0A0A0A), Color(0xFF1B1B1B))))
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo_cafe),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(2.dp),
            contentScale = ContentScale.Crop,
            alpha = 0.2f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp)
                .wrapContentSize(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
                    .background(color = Color.White.copy(alpha = 0.06f), shape = RoundedCornerShape(20.dp))
                    .padding(30.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Registro",
                        color = Color(0xFF18D92E),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = uiState.nombre,
                        onValueChange = viewModel::onNombreChange,
                        label = { Text("Ingrese su nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = uiState.correo,
                        onValueChange = viewModel::onCorreoChange,
                        label = { Text("Correo") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = uiState.password,
                        onValueChange = viewModel::onPasswordChange,
                        label = { Text("Contraseña") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = uiState.confirmarPassword,
                        onValueChange = viewModel::onConfirmarPasswordChange,
                        label = { Text("Confirmar contraseña") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            if (viewModel.canContinue()) {
                                navController.navigate(AppRoutes.Login) {
                                    popUpTo(AppRoutes.Register) { inclusive = true }
                                }
                            }
                        },
                        enabled = viewModel.canContinue(),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF18D92E)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Crear cuenta", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    TextButton(onClick = { navController.navigate(AppRoutes.Login) }) {
                        Text(
                            "¿Ya tienes cuenta? Inicia sesión aquí",
                            color = Color(0xFF18D92E),
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
