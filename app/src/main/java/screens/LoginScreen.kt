package Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agrofinanzas.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A))
    ) {
        // ====== FONDO ======
        Image(
            painter = painterResource(id = R.drawable.fondo_cafe),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(5.dp),
            contentScale = ContentScale.Crop,
            alpha = 0.25f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp)
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ====== TARJETA PRINCIPAL ======
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .shadow(20.dp, RoundedCornerShape(25.dp))
                    .background(
                        color = Color.White.copy(alpha = 0.08f),
                        shape = RoundedCornerShape(25.dp)
                    )
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                )

                Text(
                    text = "Inicio de Sesión",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF18D92E),
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // ---------------- CORREO (Ahora usa TextField) ----------------
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo Electrónico") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White),
                    colors = TextFieldDefaults.colors(
                        // Configuración para eliminar el borde y el fondo
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,

                        // Color de la línea inferior (indicador)
                        focusedIndicatorColor = Color(0xFF18D92E),
                        unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.5f),

                        // Configuraciones de color para texto y label
                        cursorColor = Color.White,
                        focusedLabelColor = Color(0xFF18D92E),
                        unfocusedLabelColor = Color.Gray,
                    )
                )

                Spacer(Modifier.height(10.dp))

                // ---------------- CONTRASEÑA (Ahora usa TextField) ----------------
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White),
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Text(
                                if (showPassword) "🔓" else "👁",
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        // Configuración para eliminar el borde y el fondo
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,

                        // Color de la línea inferior (indicador)
                        focusedIndicatorColor = Color(0xFF18D92E),
                        unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.5f),

                        // Configuraciones de color para texto y label
                        cursorColor = Color.White,
                        focusedLabelColor = Color(0xFF18D92E),
                        unfocusedLabelColor = Color.Gray,
                    )
                )

                Spacer(Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF18D92E),
                            uncheckedColor = Color.LightGray
                        )
                    )
                    Text("Recordarme", color = Color.LightGray)
                }

                Spacer(Modifier.height(20.dp))

                // ---------------- BOTÓN ----------------
                Button(
                    onClick = { navController.navigate("home") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF38EF7D),
                                        Color(0xFF11998E)
                                    )
                                ),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Iniciar sesión",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                Row {
                    Text("¿No tienes cuenta?", color = Color.LightGray)
                    Spacer(Modifier.width(5.dp))
                    Text(
                        "Regístrate aquí",
                        color = Color(0xFF18D92E),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            navController.navigate("register")
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            }
        }
}
