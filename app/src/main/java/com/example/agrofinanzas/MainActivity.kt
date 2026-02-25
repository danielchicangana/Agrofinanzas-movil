package com.example.agrofinanzas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.agrofinanzas.components.BottomBar
import com.example.agrofinanzas.screens.AppRoutes
import com.example.agrofinanzas.screens.comentarios.ComentariosScreen
import com.example.agrofinanzas.screens.cultivos.CultivosScreen
import com.example.agrofinanzas.screens.finanzas.FinanzasScreen
import com.example.agrofinanzas.screens.home.HomeScreen
import com.example.agrofinanzas.screens.login.LoginScreen
import com.example.agrofinanzas.screens.perfil.PerfilScreen
import com.example.agrofinanzas.screens.register.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route
            val hideBottomBarRoutes = setOf(AppRoutes.Login, AppRoutes.Register)

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    if (currentRoute !in hideBottomBarRoutes) {
                        BottomBar(navController)
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = AppRoutes.Login,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(AppRoutes.Login) { LoginScreen(navController) }
                    composable(AppRoutes.Register) { RegisterScreen(navController) }
                    composable(AppRoutes.Home) { HomeScreen(navController) }
                    composable(AppRoutes.Cultivos) { CultivosScreen(navController) }
                    composable(AppRoutes.Finanzas) { FinanzasScreen(navController) }
                    composable(AppRoutes.Comentarios) { ComentariosScreen(navController) }
                    composable(AppRoutes.Perfil) { PerfilScreen(navController) }
                }
            }
        }
    }
}
