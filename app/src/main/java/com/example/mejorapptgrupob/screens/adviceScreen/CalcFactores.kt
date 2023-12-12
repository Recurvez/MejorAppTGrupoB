package com.example.mejorapptgrupob.screens.adviceScreen

import androidx.compose.runtime.Composable



class CalcFactores {
    fun interpretarTest(respuestas: List<Int>): Triple<Int, Int, Int> {
        // Calcula los niveles de respuesta
        val nivelCognitivo = calcularNivelCognitivo(respuestas)
        val nivelFisico = calcularNivelFisico(respuestas)
        val nivelEvitacion = calcularNivelEvitacion(respuestas)

        return Triple(nivelCognitivo, nivelFisico, nivelEvitacion)
    }

    // Funciones para calcular los niveles de respuesta
    fun calcularNivelCognitivo(respuestasFisiologica: List<Int>): Int {
        val puntuacion = respuestasFisiologica.sum()
        return when (puntuacion) {
            in 0..14 -> 1 // O el valor que represente "bajo"
            in 15..21 -> 2 // O el valor que represente "medio"
            else -> 3 // O el valor que represente "alto"
        }
    }

    fun calcularNivelFisico(respuestasFisico: List<Int>): Int {
        val puntuacion = respuestasFisico.sum()
        return when (puntuacion) {
            in 0..14 -> 1 // O el valor que represente "bajo"
            in 15..23 -> 2 // O el valor que represente "medio"
            else -> 3 // O el valor que represente "alto"
        }
    }

    fun calcularNivelEvitacion(respuestasEvitacion: List<Int>): Int {
        val puntuacion = respuestasEvitacion.sum()
        return when (puntuacion) {
            in 0..1 -> 1 // O el valor que represente "bajo"
            in 2..3 -> 2 // O el valor que represente "medio"
            else -> 3 // O el valor que represente "alto"
        }
    }

    // Función para calcular la puntuación combinada
    fun calcularPuntuacionCombinada(
        respuestasCognitivo: List<Int>,
        respuestasFisico: List<Int>,
        respuestasEvitacion: List<Int>
    ): Int {
        return respuestasCognitivo.sum() + respuestasFisico.sum() + respuestasEvitacion.sum()
    }
}
