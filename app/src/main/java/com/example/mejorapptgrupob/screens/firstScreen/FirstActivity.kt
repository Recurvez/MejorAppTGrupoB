package com.example.mejorapptgrupob.screens.firstScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.screens.firstScreen.ui.theme.MejorAppTGrupoBTheme
import com.example.mejorapptgrupob.screens.infoScreen.InfoActivity
import com.example.mejorapptgrupob.screens.registerScreen.RegisterActivity
import com.example.mejorapptgrupob.screens.userGuideScreen.UserGuideActivity

class FirstActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FirstLayout()
                }
            }
        }
    }
}

@Composable
internal fun FirstLayout(){
    val mContext = LocalContext.current
    // Fondo que ocupa toda la pantalla
    Image(
        painter = painterResource(id = R.drawable.firstscreen_bg),
        contentDescription = "First screen background",
        alignment = Alignment.BottomCenter,
        contentScale = ContentScale.FillBounds
    )

    // Columna que ocupa toda la pantalla
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
    ){

        // Icono de arriba a la derecha
        var isMenuExpanded by remember { mutableStateOf(false) }

        CircularIconButton(
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp),
            icon = painterResource(id = R.drawable.ic_launcher_foreground),
            onClick = {
                isMenuExpanded = !isMenuExpanded
            }
        )

        // Menú de usuario
        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false },
            modifier = Modifier
                .fillMaxHeight()
                .border(1.dp, MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Menú de Usuario",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxSize()
                    .background(Color(0xFF9370DB))
            )
            Divider(color = Color.Black, thickness = 1.dp)
            DropdownMenuItem(
                text = { Text("Cambiar de Usuario") },
                onClick = { /*TODO*/ }
            )
            DropdownMenuItem(
                text = { Text("Ver resultados anteriores") },
                onClick = { /*TODO*/ }
            )
            DropdownMenuItem(
                text = { Text("Cambiar Tema") },
                onClick = { /*TODO*/ }
            )
            DropdownMenuItem(
                text = { Text("Información de la app") },
                onClick = { /*TODO*/ }
            )
        }

        // Espacio entre el icono y el título
        Spacer(modifier = Modifier.height(40.dp))

        // Título
        Text(
            text = "SALUD MENTAL",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center,
            color = Color(0xFF9370DB),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tres tarjetas con icono
        Column{
            CardWithIconClickable(
                icon = painterResource(id = R.drawable.userguide_icon),
                text = "Guía de usuario",
                background = Brush.linearGradient(colors = listOf(
                    Color(0xFFADD8E6),
                    Color(0xFF9370DB),
                    Color(0xFFBA55D3)
                ),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                ),
                onClick = {
                    mContext.startActivity(Intent(mContext, UserGuideActivity::class.java))
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            CardWithIconClickable(
                icon = painterResource(id = R.drawable.test_icon),
                text = "Realizar Test",
                background = Brush.linearGradient(colors = listOf(
                    Color(0xFF8A2BE2),
                    Color(0xFF483D8B),
                    Color(0xFF6A5ACD)
                ),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                ),
                onClick = {/*Por hacer*/}
            )

            Spacer(modifier = Modifier.height(30.dp))

            CardWithIconClickable(
                icon = painterResource(id = R.drawable.info_icon),
                text = "Información de interés",
                background = Brush.linearGradient(colors = listOf(
                    Color(0xFFADD8E6),
                    Color(0xFF9370DB),
                    Color(0xFFBA55D3)
                ),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                ),
                onClick = {
                    mContext.startActivity(Intent(mContext, InfoActivity::class.java))
                }
            )
        }
    }
}

@Composable
fun CircularIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    onClick: () -> Unit
){

    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(color = Color.Black)

    ){
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun CardWithIconClickable(
    icon: Painter,
    text: String,
    background: Brush,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(20.dp, 5.dp, 20.dp, 10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(
            modifier = Modifier
                .background(background)
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = icon,
                    contentDescription = null,
                    Modifier.size(60.dp)
                )

                Spacer(modifier = Modifier.width(32.dp))

                Text(
                    text = text,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


