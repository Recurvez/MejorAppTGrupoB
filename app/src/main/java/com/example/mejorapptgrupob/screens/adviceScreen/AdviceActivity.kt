package com.example.mejorapptgrupob.screens.adviceScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.mejorapptgrupob.screens.adviceScreen.ui.theme.MejorAppTGrupoBTheme
import kotlinx.serialization.decodeFromString
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import kotlinx.serialization.json.Json


class AdviceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MejorAppTGrupoBTheme {
                // A surface container using the 'background' color from the theme

                }
            }
        }
    }

@Composable
fun InterpretarTest(respuestas: List<Int>) {
    // Calcula los niveles de respuesta
    val nivelCognitivo = calcularNivelCognitivo(respuestas)
    val nivelFisico = calcularNivelFisico(respuestas)
    val nivelEvitacion = calcularNivelEvitacion(respuestas)


}

@Composable
// Funciones para calcular los niveles de respuesta
fun calcularNivelCognitivo(respuestas: List<Int>): String {
    val puntuacion = respuestas.sum()
    val nivel = when (puntuacion) {
        in 0..14 -> "bajo"
        in 15..21 -> "medio"
        else -> "alto"
    }
    return nivel
}

fun calcularNivelFisico(respuestas: List<Int>): String {
    val puntuacion = respuestas.sum()
    val nivel = when (puntuacion) {
        in 0..14 -> "bajo"
        in 15..23 -> "medio"
        else -> "alto"
    }
    return nivel
}

fun calcularNivelEvitacion(respuestas: List<Int>): String {
    val puntuacion = respuestas.sum()
    val nivel = when (puntuacion) {
        in 0..1 -> "bajo"
        in 2..3 -> "medio"
        else -> "alto"
    }
    return nivel
}
@Composable
// Función para calcular la puntuación combinada
fun calcularPuntuacionCombinada(
    respuestasCognitivo: List<Int>,
    respuestasFisico: List<Int>,
    respuestasEvitacion: List<Int>
): Int {
    return respuestasCognitivo.sum() + respuestasFisico.sum() + respuestasEvitacion.sum()
}

class Consejo(
    val numero: Int,
    val titulo: String,
    val texto: String
)
@Composable
fun obtenerConsejos(numerosConsejos: List<Int>): List<Consejo> {
    // Abre el archivo CSV
    val archivo = File("consejos.csv")

    // Lee el archivo CSV
    val reader = BufferedReader(FileReader(archivo))

    // Crea una lista de consejos
    val consejos = mutableListOf<Consejo>()

    // Lee cada línea del archivo CSV
    for (linea in reader.lines()) {
        // Divide la línea en un mapa
        val mapa = Json.decodeFromString(linea.split(";") as String) as Map<String, Int>

        // Comprueba si el número de consejo está en la lista
        if (numerosConsejos.contains(mapa["numero"] as Int)) {
            // Crea un consejo
            val consejo = Consejo(
                numero = mapa["numero"] as Int,
                titulo = mapa["titulo"] as String,
                texto = mapa["texto"] as String
            )

            // Añade el consejo a la lista
            consejos.add(consejo)
        }
    }

    // Cierra el archivo CSV
    reader.close()

    // Devuelve la lista de consejos
    return consejos
}













