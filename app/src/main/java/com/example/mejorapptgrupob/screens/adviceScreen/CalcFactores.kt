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
    fun calcularNivelCognitivo(respuestasFisiologica: List<Int>): Int {
        val puntuacion = respuestasFisiologica.sum()
        val nivel = when (puntuacion) {
            in 0..14 -> "bajo"
            in 15..21 -> "medio"
            else -> "alto"
        }
        return puntuacion
    }

    @Composable
    fun calcularNivelFisico(respuestasFisico: List<Int>): Int {
        val puntuacion = respuestasFisico.sum()
        val nivel = when (puntuacion) {
            in 0..14 -> "bajo"
            in 15..23 -> "medio"
            else -> "alto"
        }
        return puntuacion
    }

    @Composable
    fun calcularNivelEvitacion(respuestasEvitacion: List<Int>): Int {
        val puntuacion = respuestasEvitacion.sum()
        val nivel = when (puntuacion) {
            in 0..1 -> "bajo"
            in 2..3 -> "medio"
            else -> "alto"
        }
        return puntuacion
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
