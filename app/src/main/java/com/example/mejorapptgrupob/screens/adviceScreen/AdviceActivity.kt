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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mejorapptgrupob.R
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


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
                    val consejos = obtenerConsejos(listOf(intent.getIntExtra("numero", -1)))
                    AdviceLayout(consejos[0])
                }
            }
        }
    }
}

@Composable
internal fun AdviceLayout(consejo: Consejo) {
    val mContext = LocalContext.current

    Image(
        painter = painterResource(R.drawable.infoscreen_bg),
        contentDescription = "fondo de la pantalla de consejos",
        contentScale = ContentScale.FillBounds,
        alignment = Alignment.BottomCenter
    )

    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column( modifier = Modifier.fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(110.dp))
            Text(
                text = "¡HAS LLEGADO AL FINAL!",

                style = MaterialTheme.typography.titleLarge, fontSize = 25.sp, fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = "A continuación te dejamos unos consejos que pueden ayudarte",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,fontSize = 15.sp,fontWeight = FontWeight.Bold,
            )

        }

        Spacer(modifier = Modifier.padding(15.dp))

        Column {


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
        val mapa = Json.decodeFromString(linea.split(";").toString()) as Map<String, Int>

        // Comprueba si el número de consejo está en la lista
        if (numerosConsejos.contains(mapa["numero"] as Int)) {
            // Crea un consejo
            val consejo = Consejo(
                numero = mapa["numero"] as Int,
                titulo = mapa["titulo"].toString(),
                texto = mapa["texto"].toString()
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












