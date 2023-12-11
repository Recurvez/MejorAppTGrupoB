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
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.internalDB.DBUtilities
import com.example.mejorapptgrupob.screens.testScreen.ui.theme.MejorAppTGrupoBTheme

class TestActivity4 : ComponentActivity() {
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
                    Screen4(preguntas)
                }
            }
        }
    }
}

@Composable
internal fun Screen4(preguntas: List<String>) {

    val mContext = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }

    val textValues = listOf(
        "Selecciona una Opción",
        "Nunca", "En Pocas Ocasiones",
        "Con Cierta Frecuencia",
        "A Menudo o Casi Siempre",
        "Siempre"
    )

    var sliderPosition13 by remember { mutableStateOf(0f) }
    var sliderPosition14 by remember { mutableStateOf(0f) }
    var sliderPosition15 by remember { mutableStateOf(0f) }
    var sliderPosition16 by remember { mutableStateOf(0f) }

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
    CustomProgressBar(progress = 0.6f)
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
                    sliderPosition = sliderPosition13.toInt(),
                    onValueChange = { sliderPosition13 = it },
                    textValues = textValues,
                    labelText = preguntas.getOrNull(12) ?: ""
                )

                Spacer(modifier = Modifier.height(20.dp))

                SliderWithValueText(
                    sliderPosition = sliderPosition14.toInt(),
                    onValueChange = { sliderPosition14 = it },
                    textValues = textValues,
                    labelText = preguntas.getOrNull(13) ?: ""
                )

                Spacer(modifier = Modifier.height(20.dp))

                SliderWithValueText(
                    sliderPosition = sliderPosition15.toInt(),
                    onValueChange = { sliderPosition15 = it },
                    textValues = textValues,
                    labelText = preguntas.getOrNull(14) ?: ""
                )

                Spacer(modifier = Modifier.height(30.dp))

                SliderWithValueText(
                    sliderPosition = sliderPosition16.toInt(),
                    onValueChange = { sliderPosition16 = it },
                    textValues = textValues,
                    labelText = preguntas.getOrNull(15) ?: ""
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
                        mContext.startActivity(Intent(mContext, TestActivity3::class.java))
                    })
                    BotonSiguiente(onClick = {
                        if (sliderPosition13 != 0f && sliderPosition14 != 0f &&
                            sliderPosition15 != 0f && sliderPosition16 != 0f
                        ) {
                            // Todos los sliders tienen respuestas válidas, navegar a la siguiente pantalla
                            mContext.startActivity(Intent(mContext, TestActivity5::class.java))
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