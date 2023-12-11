package com.example.mejorapptgrupob.screens.adviceScreen

import androidx.compose.runtime.Composable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class CalcFactores {
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

    @Composable
    fun calcularNivelFisico(respuestas: List<Int>): String {
        val puntuacion = respuestas.sum()
        val nivel = when (puntuacion) {
            in 0..14 -> "bajo"
            in 15..23 -> "medio"
            else -> "alto"
        }
        return nivel
    }

    @Composable
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


}