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

class TestScreen15 {

    companion object {

        @Composable
        fun Screen15() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition43 by remember { mutableStateOf(0f) }
            var sliderPosition44 by remember { mutableStateOf(0f) }

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
                    .padding(top = 50.dp),

                contentAlignment = Alignment.Center
            ){
                Column (
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .align(Alignment.TopCenter)
                ){

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition43.toInt(),
                        onValueChange = {sliderPosition43 = it},
                        textValues = textValues,
                        labelText = "40. Desarrollo un ritual de comportamiento que me da seguridad y cuando algo me falla " +
                                "pienso que el examen se me va a dar mal."
                    )

                    Spacer(modifier = Modifier.height(40.dp))


                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition44.toInt(),
                        onValueChange = {sliderPosition44 = it},
                        textValues = textValues,
                        labelText = "41. Antes de entrar al examen hablo con mucha rapidez y me muevo más de lo habitual."
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Spacer(modifier = Modifier.height(155.dp))
                    TestScreen.CustomProgressBar(progress = 0.35f)

                }


            }

        }
    }
}