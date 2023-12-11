package com.example.mejorapptgrupob.screens.adviceScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mejorapptgrupob.R
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class AdviceActivity : ComponentActivity() {

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
                    val consejos = obtenerConsejos()
                    val consejosRecomendados = consejoRecomendado(0, 0, 0, consejos)
                    AdviceLayout(consejosRecomendados)
                }
            }
        }
    }
}

@Composable
internal fun AdviceLayout(consejos: List<Consejo>) {
    val consejos = obtenerConsejos()
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
            modifier = Modifier.fillMaxWidth(),
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

        Column {


            for (consejo in consejos) {
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

                        Button(
                            onClick = {
                                // Marca el consejo como leído
                            }
                        ) {
                            Text("Marcar como leído")
                        }
                    }
                }
            }
        }
    }
}


fun consejoRecomendado(sumC: Int, sumF: Int, sumE: Int, consejos: List<Consejo>): List<Consejo> {


    val consejosRecomendados = obtenerConsejos()


    // Crea una lista de consejos
    val consejosTodoB = mutableListOf<Consejo>()
    val consejosFacC = mutableListOf<Consejo>()
    val consejosFacF = mutableListOf<Consejo>()
    val consejosFacE = mutableListOf<Consejo>()
    val consejosFacCFE = mutableListOf<Consejo>()
    val consejosFacCF = mutableListOf<Consejo>()
    val consejosFacCE = mutableListOf<Consejo>()
    val consejosFacFE = mutableListOf<Consejo>()

    // Primera condición
    if (sumC <= 14 && sumF <= 14 && sumE <= 1) {
        for (consejo in consejos) {
            when (consejo.numero) {
                in listOf(13, 14, 15) -> consejosTodoB.add(consejo)
            }
        }
        return consejosTodoB
    }

    // Segunda condición
    if (sumC >= 15 && sumF <= 14 && sumE <= 1) {
        for (consejo in consejos) {
            when (consejo.numero) {
                in listOf(1, 2 , 3, 4 , 5) -> consejosFacC.add(consejo)
            }
        }
        return consejosFacC
    }

    // Tercera condición
    if (sumC <= 14 && sumF >= 15 && sumE <= 1) {
        for (consejo in consejos) {
            when (consejo.numero) {
                in listOf(6,7,8,9,10) -> consejosFacF.add(consejo)
            }
        }
        return consejosFacF
    }

    // Cuarta condición
    if (sumC <= 14 && sumF <= 14 && sumE >= 2) {
        for (consejo in consejos) {
            when (consejo.numero) {
                in listOf(6,3,11) -> consejosFacE.add(consejo)
            }
        }
        return consejosFacE
    }

    // Quinta condición
    if (sumC >= 15 && sumF >= 15 && sumE >= 2) {
        for (consejo in consejos) {
            when (consejo.numero) {
                in listOf(6,8,10,11,12) -> consejosFacCFE.add(consejo)
            }
        }
        return consejosFacCFE
    }

    // Sexta condición
    if (sumC >= 15 && sumF >= 15 && sumE >= 1) {
        for (consejo in consejos) {
            when (consejo.numero) {
                in listOf(2,3,6,8,9) -> consejosFacCF.add(consejo)
            }
        }
        return consejosFacCF
    }

    // Séptima condición
    if (sumC >= 15 && sumF <= 14 && sumE >= 2) {
        for (consejo in consejos) {
            when (consejo.numero) {
                in listOf(3,5,6,10,11) -> consejosFacCE.add(consejo)
            }
        }
        return consejosFacCE
    }

    // Octava condición
    if (sumC <= 14 && sumF >= 15 && sumE >= 2) {
        for (consejo in consejos) {
            when (consejo.numero) {
                in listOf(6,8,10,11) -> consejosFacFE.add(consejo)
            }
        }
        return consejosFacFE
    }

    // No se ha encontrado el consejo recomendado
    return consejosRecomendados
}


fun obtenerConsejos(): List<Consejo> {
    val inputStream = R.openRawResource(R.raw.consejos)
    // Devuelve la lista de consejos filtrados
    return readCsv(inputStream)
}


// Función para leer un archivo CSV
fun readCsv(inputStream: InputStream): List<Consejo> {


    // Lee el archivo CSV
    val reader = BufferedReader(InputStreamReader (inputStream))

    // Crea una lista de consejos
    val consejos = mutableListOf<Consejo>()

    // Lee cada línea del archivo CSV
    for (linea in reader.lines()) {
        val columnas = linea.split(";")
        val consejo = Consejo(
            numero = columnas[0].toIntOrNull() ?: 0,
            titulo = columnas[1],
            texto = columnas[2]
        )
        consejos.add(consejo)
    }

    // Cierra el archivo CSV
    reader.close()

    // Devuelve la lista de consejos
    return consejos
}

class Consejo(
    val numero: Int,
    val titulo: String,
    val texto: String
)


















