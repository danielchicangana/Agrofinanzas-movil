package Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import com.example.agrofinanzas.R // Asegúrate de que esta ruta sea correcta

@Composable
fun RegisterScreen(navController: NavController) {

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Estado para permitir el desplazamiento si el contenido no cabe en la pantalla
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFF0A0A0A), Color(0xFF1B1B1B))
                )
            )
    ) {

        // ====== FONDO ======
        Image(
            painter = painterResource(id = R.drawable.fondo_cafe),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(2.dp),
            contentScale = ContentScale.Crop,
            alpha = 0.2f
        )

        // Columna principal con desplazamiento y centrado
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState) // Permite el desplazamiento vertical
                .padding(horizontal = 24.dp) // Añade padding horizontal para no tocar los bordes
                .wrapContentSize(Alignment.Center) // Centra el contenido si la pantalla es más grande
        ) {

            // ====== TARJETA PRINCIPAL (Ahora solo ocupa un ancho máximo) ======
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f) // Ocupa el 90% del ancho disponible
                    .align(Alignment.CenterHorizontally) // Centra la tarjeta horizontalmente
                    .background(
                        color = Color.White.copy(alpha = 0.06f),
                        shape = RoundedCornerShape(20.dp)
                    )
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
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Ingrese su nombre") },
                        // Ahora ocupa el 100% del ancho de la tarjeta
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            // AQUÍ LLAMAS TU API
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF18D92E)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Registrarse", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    TextButton(onClick = {
                        navController.navigate("login")
                    }) {
                        Text("¿Ya tienes cuenta? Inicia sesión aquí",
                            color = Color(0xFF18D92E),
                            fontSize = 14.sp)
                    }
                }
            }
            // Espacio extra al final para mejorar el 'scroll' en móvil
            Spacer(modifier = Modifier.height(30.dp))
            }
        }
}
