package com.example.mejorapptgrupob.screens.adviceScreen

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.screens.firstScreen.FirstActivity
import com.example.mejorapptgrupob.screens.testScreen.GlobalLists
import com.example.mejorapptgrupob.screens.testScreen.SliderUtility
import com.example.mejorapptgrupob.screens.testScreen.SliderUtility2
import com.example.mejorapptgrupob.screens.testScreen.SliderUtility3
import com.example.mejorapptgrupob.screens.testScreen.SliderUtility4
import com.example.mejorapptgrupob.screens.testScreen.SliderUtility5
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import androidx.compose.ui.platform.LocalContext

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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val respuestas = listOf(3, 5, 1, 2, 4)

                    val factorCalculator = CalcFactores()

                    val nivelCognitivo = factorCalculator.calcularNivelCognitivo(respuestas)
                    val nivelFisico = factorCalculator.calcularNivelFisico(respuestas)
                    val nivelEvitacion = factorCalculator.calcularNivelEvitacion(respuestas)

                    val sumC = GlobalLists.respuestasCognitiva.sum()
                    val sumF = GlobalLists.respuestasFisiologica.sum()
                    val sumE = GlobalLists.respuestasEvitacion.sum()

                    val context = this@AdviceActivity
                    val consejos = obtenerConsejos(context)
                    val consejosRecomendados = consejoRecomendado(
                        sumC,
                        sumF,
                        sumE,
                        consejos,
                        context
                        // O applicationContext si necesario
                    )


                    var navigateToFirstScreen by remember { mutableStateOf(false) }

                    AdviceLayout(
                        consejosRecomendados, onBackButtonClick = {
                            navigateToFirstScreen = true
                        },
                        context = this@AdviceActivity
                    )

                    if (navigateToFirstScreen) {
                        val intent = Intent(context, FirstActivity::class.java)
                        startActivity(intent)
                    }
                }
            }

        }
    }

    @Composable
    internal fun AdviceLayout(
        consejos: List<Consejo>,
        onBackButtonClick: () -> Unit,
        context: Context
    ) {
        Image(
            painter = painterResource(R.drawable.infoscreen_bg),
            contentDescription = "fondo de la pantalla de consejos",
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.BottomCenter
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                contentPadding = PaddingValues(bottom = 25.dp), // Deja espacio para el botón
                verticalArrangement = Arrangement.spacedBy(5.dp) // Espacio entre elementos
            ) {
                items(consejos) { consejo ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFF9F21379E), // Color inicial del gradiente
                                        Color(0xFF21379E)    // Color final del gradiente
                                    ),
                                    startY = 0f,
                                    endY = 1f
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0xFF9F21379E), // Color inicial del gradiente
                                            Color(0xFF21379E)    // Color final del gradiente
                                        )
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(16.dp)
                        ) {
                            Text(
                                text = consejo.titulo,
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color.White // Ajusta el color del texto a blanco
                            )
                            Text(
                                text = consejo.texto,
                                style = MaterialTheme.typography.bodyLarge,
                                maxLines = Int.MAX_VALUE,
                                color = Color.White // Ajusta el color del texto a blanco
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp)) // O el espacio que desees entre el LazyColumn y el botón

            Button(
                onClick = {
                    val intent = Intent(context, FirstActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF9F21379E), // Color inicial del gradiente
                                Color(0xFF21379E)    // Color final del gradiente
                            )
                        ),
                        shape = RoundedCornerShape(8.dp)

                    ),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "botón para ir al inicio"
                    )
                    Text(
                        "Pantalla de Inicio",
                        color = Color.White
                    ) // Ajusta el color del texto a blanco
                }
            }
        }
    }


    fun consejoRecomendado(
        sumC: Int, sumF: Int, sumE: Int, consejos: List<Consejo>, context: Context
    ): List<Consejo> {

        val consejosRecomendados = obtenerConsejos(context)

        // Crea una lista de consejos
        val consejosFiltrados = mutableListOf<Consejo>()

        for (consejo in consejos) {
            when {
                sumC <= 14 && sumF <= 14 && sumE <= 1 && consejo.numero in listOf(
                    13, 14, 15
                ) -> consejosFiltrados.add(consejo)

                sumC >= 15 && sumF <= 14 && sumE <= 1 && consejo.numero in listOf(
                    1, 2, 3, 4, 5
                ) -> consejosFiltrados.add(consejo)

                sumC <= 14 && sumF >= 15 && sumE <= 1 && consejo.numero in listOf(
                    6, 7, 8, 9, 10
                ) -> consejosFiltrados.add(consejo)

                sumC <= 14 && sumF <= 14 && sumE >= 2 && consejo.numero in listOf(
                    6, 3, 11
                ) -> consejosFiltrados.add(consejo)

                sumC >= 15 && sumF >= 15 && sumE >= 2 && consejo.numero in listOf(
                    6, 8, 10, 11, 12
                ) -> consejosFiltrados.add(consejo)

                sumC >= 15 && sumF >= 15 && sumE >= 1 && consejo.numero in listOf(
                    2, 3, 6, 8, 9
                ) -> consejosFiltrados.add(consejo)

                sumC >= 15 && sumF <= 14 && sumE >= 2 && consejo.numero in listOf(
                    3, 5, 6, 10, 11
                ) -> consejosFiltrados.add(consejo)

                sumC <= 14 && sumF >= 15 && sumE >= 2 && consejo.numero in listOf(
                    6, 8, 10, 11
                ) -> consejosFiltrados.add(consejo)
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
        val numero: Int, val titulo: String, val texto: String
    )

}
