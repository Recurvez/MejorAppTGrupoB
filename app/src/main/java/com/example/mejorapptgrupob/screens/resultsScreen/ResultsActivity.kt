package com.example.mejorapptgrupob.screens.resultsScreen

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.TagFaces
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mejorapptgrupob.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ResultsActivity : ComponentActivity() {
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
                    ResultsLayout()
                }
            }
        }
    }
}

data class Results(
    val id: Int,
    val nick: String,
    val sumFact01: Int,
    val sumFact02: Int,
    val sumFact03: Int,
    val fecha: String
) {
    val nivelFact01: String
        get() = calcularNivel(sumFact01, 0, 14, 15, 21, 22, 28)

    val nivelFact02: String
        get() = calcularNivel(sumFact02, 0, 14, 15, 23, 24, 36)

    val nivelFact03: String
        get() = calcularNivel(sumFact03, 0, 1, 2, 3, 4, 16)

    val iconoFact01: ImageVector
        get() = obtenerIconoNivel(sumFact01, 0, 14, 15, 21, 22, 28)

    val iconoFact02: ImageVector
        get() = obtenerIconoNivel(sumFact02, 0, 14, 15, 23, 24, 36)

    val iconoFact03: ImageVector
        get() = obtenerIconoNivel(sumFact03, 0, 1, 2, 3, 4, 16)

    private fun calcularNivel(valor: Int, bajoInicio: Int, bajoFin: Int, medioInicio: Int, medioFin: Int, altoInicio: Int, altoFin: Int): String {
        return when {
            valor in bajoInicio..bajoFin -> "Nivel bajo "
            valor in medioInicio..medioFin -> "Nivel medio "
            valor in altoInicio..altoFin -> "Nivel alto "
            else -> "No definido"
        }
    }

    private fun obtenerIconoNivel(valor: Int, bajoInicio: Int, bajoFin: Int, medioInicio: Int, medioFin: Int, altoInicio: Int, altoFin: Int): ImageVector {
        return when {
            valor in bajoInicio..bajoFin -> Icons.Default.TagFaces
            valor in medioInicio..medioFin -> Icons.Default.Warning
            valor in altoInicio..altoFin -> Icons.Default.Error
            else -> Icons.Default.Warning
        }
    }
}


@Composable
fun ResultsLayout() {
    val mContext = LocalContext.current

    // Obtener el usuario actual
    val currentUser = FirebaseAuth.getInstance().currentUser

    // Estado para almacenar los resultados obtenidos de Firebase
    var resultadosList by remember { mutableStateOf(emptyList<Results>()) }

    // Estado para indicar si la conexión a Firebase está en progreso
    var isLoading by remember { mutableStateOf(true) }

    // Obtener resultados desde Firebase al iniciar la composición
    LaunchedEffect(Unit) {
        isLoading = true

        // Obtener el ID del usuario actual
        val userNick = currentUser?.email ?: ""

        resultadosList = getResultsFromFirebase(userNick)

        isLoading = false
    }

    // Fondo que ocupa toda la pantalla
    Image(
        painter = painterResource(id = R.drawable.firstscreen_bg),
        contentDescription = "First screen background",
        alignment = Alignment.BottomCenter,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )

    // Box que ocupa toda la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Mostrar mensaje de espera si está cargando
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Resultados anteriores",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                )

                if (resultadosList.isEmpty()) {
                    Text(
                        text = "Sin resultados. No se ha realizado ningún test en esta cuenta.",
                        modifier = Modifier
                            .padding(16.dp)
                    )
                } else {
                    ShowResultCards(resultadosList)
                }
            }
        }
    }
}

@Composable
fun ShowResultCards(resultadosList: List<Results>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize() // Adjust the weight to allow scrolling
    ) {
        items(resultadosList) { resultados ->
            ResultCard(resultados)
        }
    }
}

@Composable
fun ResultCard(resultados: Results) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Fecha: ${resultados.fecha}")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Nivel Cognitivo: ${resultados.sumFact01} - ${resultados.nivelFact01}")
                Icon(
                    imageVector = resultados.iconoFact01,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Nivel Físico: ${resultados.sumFact02} - ${resultados.nivelFact02}")
                Icon(
                    imageVector = resultados.iconoFact02,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Nivel Evitación: ${resultados.sumFact03} - ${resultados.nivelFact03}")
                Icon(
                    imageVector = resultados.iconoFact03,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// Función para obtener los resultados desde Firebase
suspend fun getResultsFromFirebase(userNick: String): List<Results> {
    val db = FirebaseFirestore.getInstance()
    val resultadosList = mutableListOf<Results>()

    try {
        // Realizar la consulta a la colección "resultados"
        val result = db.collection("results")
            .whereEqualTo("nick", userNick)  // Filtrar por nick
            .get()
            .await()

        for (document in result) {
            // Procesar los documentos recuperados y agregar a resultadosList
            val id = document.getLong("id")?.toInt() ?: 0
            val nick = document.getString("nick") ?: ""
            val sumFact01 = document.getLong("sumFact01")?.toInt() ?: 0
            val sumFact02 = document.getLong("sumFact02")?.toInt() ?: 0
            val sumFact03 = document.getLong("sumFact03")?.toInt() ?: 0
            val fecha = document.getString("fecha") ?: ""

            resultadosList.add(Results(id, nick, sumFact01, sumFact02, sumFact03, fecha))
        }
    } catch (exception: Exception) {
        // Manejar errores
    }

    return resultadosList
}
