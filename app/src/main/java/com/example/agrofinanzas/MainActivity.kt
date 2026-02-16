package com.example.agrofinanzas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrofinanzas.components.BottomBar
import com.example.agrofinanzas.screens.home.HomeScreen
import com.example.agrofinanzas.screens.login.LoginScreen
import com.example.agrofinanzas.screens.register.RegisterScreen
import com.example.agrofinanzas.screens.cultivos.CultivosScreen
import com.example.agrofinanzas.screens.finanzas.FinanzasScreen
import com.example.agrofinanzas.screens.comentarios.ComentariosScreen
import com.example.agrofinanzas.screens.perfil.PerfilScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    val currentRoute = navController.currentBackStackEntry?.destination?.route
                    if (currentRoute != "screens/login" && currentRoute != "screens/register") {
                        BottomBar(navController)
                    }
                }
            ) { innerPadding ->  // 👈 Aquí recibimos el padding
                NavHost(
                    navController = navController,
                    startDestination = "screens/login",
                    modifier = Modifier.padding(innerPadding)  // 👈 Aplicamos el padding al NavHost
                ) {
                    composable("screens/login") {
                        LoginScreen(navController)
                    }

                    composable("screens/register") {
                        RegisterScreen(navController)
                    }

                    composable("screens/home") {
                        HomeScreen(navController)
                    }

                    composable("screens/cultivos") {
                        CultivosScreen(navController)
                    }

                    composable("screens/finanzas") {
                        FinanzasScreen(navController)
                    }

                    composable("screens/Comentarios") {
                        ComentariosScreen(navController)
                    }

                    composable("screens/perfil") {
                        PerfilScreen(navController)
                    }
                }
            }
        }
    }
}