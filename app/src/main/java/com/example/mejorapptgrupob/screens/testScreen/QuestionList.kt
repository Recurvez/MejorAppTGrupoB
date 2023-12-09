package com.example.mejorapptgrupob.screens.testScreen

import android.content.Context
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class QuestionList {
    companion object {
        fun readCSV(inputStream: InputStream, context: Context): List<String> {
            val questionsList = mutableListOf<String>()
            val reader = BufferedReader(InputStreamReader(inputStream))

            // Lee cada línea del archivo CSV y agrega la pregunta a la lista
            reader.useLines { lines ->
                lines.forEach { line ->
                    val parts = line.split(";")
                    if (parts.size >= 4) {
                        val questionText = parts[1] // El índice 1 contiene la pregunta
                        questionsList.add(questionText)
                    }
                }
            }
            return questionsList
        }
    }
}
