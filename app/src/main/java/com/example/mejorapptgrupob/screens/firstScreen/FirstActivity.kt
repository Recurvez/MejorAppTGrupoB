package com.example.mejorapptgrupob.screens.firstScreen

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.datastore.preferences.core.edit
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.outlined.Assessment
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.ModeNight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.example.mejorapptgrupob.DataStoreManager
import com.example.mejorapptgrupob.DataStoreManager.dataStore
import com.example.mejorapptgrupob.MainActivity
import com.example.mejorapptgrupob.MainLayout
import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.screens.firstScreen.ui.theme.MejorAppTGrupoBTheme
import com.example.mejorapptgrupob.screens.firstScreen.ui.theme.Purple40
import com.example.mejorapptgrupob.screens.infoScreen.InfoActivity
import com.example.mejorapptgrupob.screens.registerScreen.RegisterActivity
import com.example.mejorapptgrupob.screens.testScreen.TestActivity

import com.example.mejorapptgrupob.screens.testScreen.ui.theme.Pink40
import com.example.mejorapptgrupob.screens.testScreen.ui.theme.Pink80
import com.example.mejorapptgrupob.screens.testScreen.ui.theme.Purple80
import com.example.mejorapptgrupob.screens.testScreen.ui.theme.PurpleGrey80
import com.example.mejorapptgrupob.screens.userGuideScreen.UserGuideActivity
import kotlinx.coroutines.launch
import java.util.prefs.Preferences

class FirstActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bloqueo de orientación
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT



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

    override fun onResume() {
        super.onResume()
        val dataStore = DataStoreManager.dataStore
    }
    fun clearPreferences() {
        lifecycleScope.launch {
            dataStore?.edit { preferences ->
                preferences.clear()
            }
        }
    }
}


@Composable
internal fun FirstLayout(){



    var model: LogoutViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

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

        // Menú de usuario
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.TopEnd
        ){
            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false },
                modifier = Modifier
                    .fillMaxHeight()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFB3CCEC), // Azul claro
                                Color(0xFF8AA1D0), // Azul medio
                                Color(0xFF5E75B4), // Azul oscuro
                            ),
                        )
                    )
                ) {
                Text(
                    text = "Ajustes de Usuario",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                )

                /*
                DropdownMenuItem(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .background(color= Purple80, shape = RoundedCornerShape(20.dp)),
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "Cambiar de Usuario",
                            )
                        }
                    },
                    onClick = { /* TO-DO */ }
                )
                */
                DropdownMenuItem(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .background(color = Purple80, shape = RoundedCornerShape(20.dp)),
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Assessment,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "Resultados anteriores",
                            )
                        }
                    },
                    onClick = { /* TO-DO */ }
                )

                DropdownMenuItem(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .background(color = Purple80, shape = RoundedCornerShape(20.dp)),
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ModeNight,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "Cambiar tema",
                            )
                        }
                    },
                    onClick = { /* TO-DO */ }
                )

                DropdownMenuItem(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .background(color = Purple80, shape = RoundedCornerShape(20.dp)),
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "Información de la app",
                            )
                        }
                    },
                    onClick = { /* TO-DO */ }
                )

                DropdownMenuItem(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .background(color = PurpleGrey80, shape = RoundedCornerShape(20.dp)),
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "Cerrar sesión",
                            )
                        }
                           },
                    onClick = {
                        // (mContext as? FirstActivity)?.clearPreferences(
                        model.logout()
                        mContext.startActivity(Intent(mContext, MainActivity::class.java))
                        (mContext as? Activity)?.finish()
                    }
                )
            }
        }

        CircularIconButton(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(8.dp),
            onClick = {
                isMenuExpanded = !isMenuExpanded
            }
        )



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
                onClick = {mContext.startActivity(Intent(mContext, TestActivity::class.java))}
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
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(color = Purple40)
    ) {
        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(30.dp)
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