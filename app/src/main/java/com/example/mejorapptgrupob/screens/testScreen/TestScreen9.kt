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

class TestScreen9 {

    companion object {


        @Composable
        fun Screen9() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition25 by remember { mutableStateOf(0f) }
            var sliderPosition26 by remember { mutableStateOf(0f) }
            var sliderPosition27 by remember { mutableStateOf(0f) }

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
                    .padding(top = 55.dp),

                contentAlignment = Alignment.Center
            ){
                Column (
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .align(Alignment.TopCenter)
                ){

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition25.toInt(),
                        onValueChange = {sliderPosition25 = it},
                        textValues = textValues,
                        labelText = "25. Me pone nervioso/a que el aula esté llena de gente."
                    )

                    Spacer(modifier = Modifier.height(30.dp))


                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition26.toInt(),
                        onValueChange = {sliderPosition26 = it},
                        textValues = textValues,
                        labelText = "26. He sentido mareos y ganas de vomitar en un examen."
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition27.toInt(),
                        onValueChange = {sliderPosition27 = it},
                        textValues = textValues,
                        labelText = "27. Momentos antes de hacer el examen tengo la boca seca y me cuesta tragar"
                    )

                    Spacer(modifier = Modifier.height(60.dp))
                    TestScreen.CustomProgressBar(progress = 0.35f)

                }


            }

        }
    }
}