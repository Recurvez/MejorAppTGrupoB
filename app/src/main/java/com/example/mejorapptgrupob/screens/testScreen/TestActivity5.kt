package com.example.mejorapptgrupob.screens.testScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.internalDB.DBUtilities

class TestActivity5 : ComponentActivity() {
    private lateinit var preguntas: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dbUtilities = DBUtilities(resources.openRawResource(R.raw.preguntas),this)
        val inputStream = resources.openRawResource(R.raw.preguntas)
        preguntas = QuestionList.readCSV(inputStream, this)
        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen5(preguntas)
                }
            }
        }
    }
}

@Composable
internal fun Screen5(preguntas: List<String>) {

    val mContext = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }

    val textValues = listOf(
        "Selecciona una Opción",
        "Nunca", "En Pocas Ocasiones",
        "Con Cierta Frecuencia",
        "A Menudo o Casi Siempre",
        "Siempre"
    )

    var sliderPosition17 by remember { mutableStateOf(0f) }
    var sliderPosition18 by remember { mutableStateOf(0f) }
    var sliderPosition19 by remember { mutableStateOf(0f) }
    var sliderPosition20 by remember { mutableStateOf(0f) }

    Image(
        painter = painterResource(id = R.drawable.testscreen2_bg),
        contentDescription = "background for test screen",
        alignment = Alignment.BottomCenter,
        contentScale = ContentScale.FillBounds
    )

    /*Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.cup),
            contentDescription = "cup image",
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.BottomEnd)
                .offset((-15).dp, (-15).dp)
        )

    }
    */
    CustomProgressBar(progress = 0.8f)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Top,
            contentPadding = PaddingValues(top = 20.dp, bottom = 60.dp)
        ) {

            item {
                SliderWithValueText(
                    sliderPosition = sliderPosition17.toInt(),
                    onValueChange = { sliderPosition17 = it },
                    textValues = textValues,
                    labelText = preguntas.getOrNull(16) ?: ""
                )

                Spacer(modifier = Modifier.height(20.dp))

                SliderWithValueText(
                    sliderPosition = sliderPosition18.toInt(),
                    onValueChange = { sliderPosition18 = it },
                    textValues = textValues,
                    labelText = preguntas.getOrNull(17) ?: ""
                )

                Spacer(modifier = Modifier.height(20.dp))

                SliderWithValueText(
                    sliderPosition = sliderPosition19.toInt(),
                    onValueChange = { sliderPosition19 = it },
                    textValues = textValues,
                    labelText = preguntas.getOrNull(18) ?: ""
                )

                Spacer(modifier = Modifier.height(30.dp))

                SliderWithValueText(
                    sliderPosition = sliderPosition20.toInt(),
                    onValueChange = { sliderPosition20 = it },
                    textValues = textValues,
                    labelText = preguntas.getOrNull(19) ?: ""
                )

                Spacer(modifier = Modifier.height(30.dp))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BotonAtras(onClick = {
                        mContext.startActivity(Intent(mContext, TestActivity4::class.java))
                    })
                    BotonSiguiente(onClick = {
                        if (sliderPosition17 != 0f && sliderPosition18 != 0f &&
                            sliderPosition19 != 0f && sliderPosition20 != 0f
                        ) {
                            // Todos los sliders tienen respuestas válidas, navegar a la siguiente pantalla
                            //Aquí intent hacia la pantalla de resultados
                        } else {
                            openDialog.value = true
                        }
                    })
                    AlertDialogExample(openDialog)
                }
            }
        }
    }
}