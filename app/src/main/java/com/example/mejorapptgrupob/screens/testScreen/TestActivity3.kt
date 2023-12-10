package com.example.mejorapptgrupob.screens.testScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

object SliderUtility3 {
    fun resetSliderValues(context: Context) {
        saveSliderValue(context, "sliderPosition9", 0f)
        saveSliderValue(context, "sliderPosition10", 0f)
        saveSliderValue(context, "sliderPosition11", 0f)
        saveSliderValue(context, "sliderPosition12", 0f)
    }
}
class TestActivity3 : ComponentActivity() {
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
                    Screen3(this@TestActivity3, preguntas)
                }
            }
        }
    }
}

@Composable
internal fun Screen3(mContext: Context, preguntas: List<String>) {


    val mContext = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }

    val textValues = listOf(
        "Selecciona una Opción",
        "Nunca", "En Pocas Ocasiones",
        "Con Cierta Frecuencia",
        "A Menudo o Casi Siempre",
        "Siempre"
    )


    var sliderPosition9 by remember { mutableStateOf(loadSliderValue(mContext, "sliderPosition9")) }
    var sliderPosition10 by remember { mutableStateOf(loadSliderValue(mContext, "sliderPosition10")) }
    var sliderPosition11 by remember { mutableStateOf(loadSliderValue(mContext, "sliderPosition11")) }
    var sliderPosition12 by remember { mutableStateOf(loadSliderValue(mContext, "sliderPosition12")) }


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
    CustomProgressBar(progress = 0.4f)
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
                    sliderPosition = sliderPosition9.toInt(),
                    onValueChange = { sliderPosition9 = it
                        saveSliderValue(mContext, "sliderPosition9", it)},
                    textValues = textValues,
                    labelText = preguntas.getOrNull(8) ?: ""
                )

                Spacer(modifier = Modifier.height(20.dp))

                SliderWithValueText(
                    sliderPosition = sliderPosition10.toInt(),
                    onValueChange = { sliderPosition10 = it
                        saveSliderValue(mContext, "sliderPosition10", it)},
                    textValues = textValues,
                    labelText = preguntas.getOrNull(9) ?: ""
                )

                Spacer(modifier = Modifier.height(20.dp))

                SliderWithValueText(
                    sliderPosition = sliderPosition11.toInt(),
                    onValueChange = { sliderPosition11 = it
                        saveSliderValue(mContext, "sliderPosition11", it)},
                    textValues = textValues,
                    labelText = preguntas.getOrNull(10) ?: ""
                )

                Spacer(modifier = Modifier.height(30.dp))

                SliderWithValueText(
                    sliderPosition = sliderPosition12.toInt(),
                    onValueChange = { sliderPosition12 = it
                        saveSliderValue(mContext, "sliderPosition12", it)},
                    textValues = textValues,
                    labelText = preguntas.getOrNull(11) ?: ""
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
                        mContext.startActivity(Intent(mContext, TestActivity2::class.java))
                    })
                    BotonSiguiente(onClick = {
                        if (sliderPosition9 != 0f && sliderPosition10 != 0f &&
                            sliderPosition11 != 0f && sliderPosition12 != 0f
                        ) {
                            GlobalLists.respuestasCognitiva[3] = mapearValor(sliderPosition9)
                            GlobalLists.respuestasEvitacion[1] = mapearValor(sliderPosition10)
                            GlobalLists.respuestasFisiologica[4] = mapearValor(sliderPosition11)
                            GlobalLists.respuestasCognitiva[4] = mapearValor(sliderPosition12)

                            // Todos los sliders tienen respuestas válidas, navegar a la siguiente pantalla
                            mContext.startActivity(Intent(mContext, TestActivity4::class.java))
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