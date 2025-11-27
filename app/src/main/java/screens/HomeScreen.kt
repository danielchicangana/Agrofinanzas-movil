package Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agrofinanzas.R

@Composable
fun HomeScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    listOf(Color(0xFF0A0A0A), Color(0xFF1B1B1B))
                )
            )
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp)
        ) {

            // ✅ IMAGEN PRINCIPAL
            item {
                Image(
                    painter = painterResource(id = R.drawable.photo_principal), // cambia por tu imagen
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            }

            // ✅ TÍTULO
            item {
                Text(
                    text = "Bienvenido a AgroFinanzas",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF18D92E),
                    modifier = Modifier.padding(20.dp)
                )
            }

            // ✅ LISTA DE NOTICIAS
            items(getNoticias()) { noticia ->
                NewsCard(
                    title = noticia.titulo,
                    desc = noticia.descripcion,
                    image = noticia.imagen
                )
            }
        }

        // ✅ BARRA INFERIOR MÓVIL
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            BottomBar(navController)
        }
    }
}

/* -------------------------------------------------- */
/* ------------------ NOTICIAS DATA ------------------ */
/* -------------------------------------------------- */

data class Noticia(
    val titulo: String,
    val descripcion: String,
    val imagen: Int
)

fun getNoticias(): List<Noticia> {
    return listOf(
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
}

/* -------------------------------------------------- */
/* ------------------ TARJETA NOTICIA ---------------- */
/* -------------------------------------------------- */

@Composable
fun NewsCard(title: String, desc: String, image: Int) {

    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .background(
                Color.White.copy(alpha = 0.06f),
                RoundedCornerShape(15.dp)
            )
            .padding(15.dp)
    ) {

        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(15.dp))

        Column {
            Text(
                text = title,
                color = Color(0xFF18D92E),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = desc,
                color = Color.LightGray,
                fontSize = 14.sp
            )
        }
    }
}

/* -------------------------------------------------- */
/* ------------------ BARRA INFERIOR ----------------- */
/* -------------------------------------------------- */

@Composable
fun BottomBar(navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(Color(0xFF0B0B0B)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BottomItem(icon = Icons.Default.Home) {
            navController.navigate("home")
        }

        BottomItem(icon = Icons.Default.AttachMoney) {
            navController.navigate("finanzas")
        }

        BottomItem(icon = Icons.Default.Eco) {
            navController.navigate("agronomia")
        }

        BottomItem(icon = Icons.Default.Chat) {
            navController.navigate("comentarios")
        }

        BottomItem(icon = Icons.Default.Person) {
            navController.navigate("perfil")
        }
    }
}

@Composable
fun BottomItem(icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF18D92E),
            modifier = Modifier.size(28.dp)
            )
        }
}
