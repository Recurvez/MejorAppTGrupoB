package com.example.mejorapptgrupob.screens.adviceScreen

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.screens.testScreen.GlobalLists
import com.example.mejorapptgrupob.screens.testScreen.SliderUtility
import com.example.mejorapptgrupob.screens.testScreen.SliderUtility2
import com.example.mejorapptgrupob.screens.testScreen.SliderUtility3
import com.example.mejorapptgrupob.screens.testScreen.SliderUtility4
import com.example.mejorapptgrupob.screens.testScreen.SliderUtility5
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class AdviceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bloqueo de orientación
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        SliderUtility.resetSliderValues(this)
        SliderUtility2.resetSliderValues(this)
        SliderUtility3.resetSliderValues(this)
        SliderUtility4.resetSliderValues(this)
        SliderUtility5.resetSliderValues(this)


        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val respuestas = listOf(3, 5, 1, 2, 4)

                    val factorCalculator = CalcFactores()

                    val nivelCognitivo = factorCalculator.calcularNivelCognitivo(respuestas)
                    val nivelFisico = factorCalculator.calcularNivelFisico(respuestas)
                    val nivelEvitacion = factorCalculator.calcularNivelEvitacion(respuestas)

                    val sumC = GlobalLists.respuestasCognitiva.sum()
                    val sumF = GlobalLists.respuestasFisiologica.sum()
                    val sumE = GlobalLists.respuestasEvitacion.sum()

                    val context = this // O también puedes usar applicationContext
                    val consejos = obtenerConsejos(context)
                    val consejosRecomendados = consejoRecomendado(
                        sumC,
                        sumF,
                        sumE,
                        consejos,
                        // O applicationContext si necesario
                    )

                    AdviceLayout(consejosRecomendados)
                }
            }
        }
    }

    @Composable
    internal fun AdviceLayout(consejos: List<Consejo>) {
        Image(
            painter = painterResource(R.drawable.infoscreen_bg),
            contentDescription = "fondo de la pantalla de consejos",
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.BottomCenter
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(110.dp))
                Text(
                    text = "¡HAS LLEGADO AL FINAL!",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.padding(5.dp))

                Text(
                    text = "A continuación te dejamos unos consejos que pueden ayudarte",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.padding(15.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(consejos) { consejo ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = consejo.titulo,
                                style = MaterialTheme.typography.headlineMedium
                            )
                            Text(
                                text = consejo.texto,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }

        }
    }

    fun consejoRecomendado(sumC: Int, sumF: Int, sumE: Int, consejos: List<Consejo>): List<Consejo> {

        val consejosRecomendados = obtenerConsejos(this)

        // Crea una lista de consejos
        val consejosFiltrados = mutableListOf<Consejo>()

        for (consejo in consejos) {
            when {
                sumC <= 14 && sumF <= 14 && sumE <= 1 && consejo.numero in listOf(13, 14, 15) -> consejosFiltrados.add(consejo)
                sumC >= 15 && sumF <= 14 && sumE <= 1 && consejo.numero in listOf(1, 2, 3, 4, 5) -> consejosFiltrados.add(consejo)
                sumC <= 14 && sumF >= 15 && sumE <= 1 && consejo.numero in listOf(6, 7, 8, 9, 10) -> consejosFiltrados.add(consejo)
                sumC <= 14 && sumF <= 14 && sumE >= 2 && consejo.numero in listOf(6, 3, 11) -> consejosFiltrados.add(consejo)
                sumC >= 15 && sumF >= 15 && sumE >= 2 && consejo.numero in listOf(6, 8, 10, 11, 12) -> consejosFiltrados.add(consejo)
                sumC >= 15 && sumF >= 15 && sumE >= 1 && consejo.numero in listOf(2, 3, 6, 8, 9) -> consejosFiltrados.add(consejo)
                sumC >= 15 && sumF <= 14 && sumE >= 2 && consejo.numero in listOf(3, 5, 6, 10, 11) -> consejosFiltrados.add(consejo)
                sumC <= 14 && sumF >= 15 && sumE >= 2 && consejo.numero in listOf(6, 8, 10, 11) -> consejosFiltrados.add(consejo)
            }
        }

        // Si hay consejos filtrados, retornarlos, de lo contrario, retornar consejos por defecto
        return if (consejosFiltrados.isNotEmpty()) consejosFiltrados else consejosRecomendados
    }
    fun obtenerConsejos(context: Context): List<Consejo> {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.consejos)
        return readCsv(inputStream)
    }


    // Función para leer un archivo CSV
    fun readCsv(inputStream: InputStream): List<Consejo> {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val consejos = mutableListOf<Consejo>()

        reader.useLines { lines ->
            lines.forEach { line ->
                val columnas = line.split(";")
                if (columnas.size >= 3) {
                    val consejo = Consejo(
                        numero = columnas[0].toIntOrNull() ?: 0,
                        titulo = columnas[1],
                        texto = columnas[2]
                    )
                    consejos.add(consejo)
                } else {
                    // Manejar líneas con un número incorrecto de columnas
                    // Por ejemplo, aquí podrías agregar un consejo por defecto o ignorar la línea
                    val consejoPorDefecto = Consejo(
                        numero = 0,
                        titulo = "Consejo por defecto",
                        texto = "Esta línea tiene un formato incorrecto"
                    )
                    consejos.add(consejoPorDefecto)
                }
            }
        }

        reader.close()
        return consejos
    }


    class Consejo(
        val numero: Int,
        val titulo: String,
        val texto: String
    )
}