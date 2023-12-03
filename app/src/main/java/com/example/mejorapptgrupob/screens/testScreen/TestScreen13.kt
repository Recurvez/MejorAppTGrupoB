package com.example.mejorapptgrupob.screens.testScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mejorapptgrupob.R

class TestScreen13 {

    companion object {

        @Composable
        fun Screen13() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition37 by remember { mutableStateOf(0f) }
            var sliderPosition38 by remember { mutableStateOf(0f) }
            var sliderPosition39 by remember { mutableStateOf(0f) }

            Image(
                painter = painterResource(id = R.drawable.testscreen2_bg),
                contentDescription = "background for test screen",
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.FillBounds
            )

            Box(
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


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),

                contentAlignment = Alignment.Center
            ){
                Column (
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .align(Alignment.TopCenter)
                ){

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition37.toInt(),
                        onValueChange = {sliderPosition37 = it},
                        textValues = textValues,
                        labelText = "37. Tardo mucho tiempo en decidirme por contestar la mayoría de las preguntas o por " +
                                "entregar el examen."
                    )

                    Spacer(modifier = Modifier.height(20.dp))


                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition38.toInt(),
                        onValueChange = {sliderPosition38 = it},
                        textValues = textValues,
                        labelText = "38. Me resulta difícil o imposible ingerir alimentos antes de realizar un examen."
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition39.toInt(),
                        onValueChange = {sliderPosition39 = it},
                        textValues = textValues,
                        labelText = "39. En las horas/momentos previos al examen siento molestias en el estómago o se me " +
                                "descompone y tengo que ir al baño."
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                    TestScreen.CustomProgressBar(progress = 0.35f)

                }


            }

        }
    }
}