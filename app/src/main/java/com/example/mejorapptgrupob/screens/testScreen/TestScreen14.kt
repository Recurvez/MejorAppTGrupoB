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

class TestScreen14 {

    companion object {

        @Composable
        fun Screen14() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition40 by remember { mutableStateOf(0f) }
            var sliderPosition41 by remember { mutableStateOf(0f) }
            var sliderPosition42 by remember { mutableStateOf(0f) }

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
                    .padding(top = 30.dp),

                contentAlignment = Alignment.Center
            ){
                Column (
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .align(Alignment.TopCenter)
                ){

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition40.toInt(),
                        onValueChange = {sliderPosition40 = it},
                        textValues = textValues,
                        labelText = "40. Desarrollo un ritual de comportamiento que me da seguridad y cuando algo me falla " +
                                "pienso que el examen se me va a dar mal."
                    )

                    Spacer(modifier = Modifier.height(30.dp))


                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition41.toInt(),
                        onValueChange = {sliderPosition41 = it},
                        textValues = textValues,
                        labelText = "41. Antes de entrar al examen hablo con mucha rapidez y me muevo más de lo habitual."
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition42.toInt(),
                        onValueChange = {sliderPosition42 = it},
                        textValues = textValues,
                        labelText = "42. La noche anterior al examen o la misma mañana me asaltan pensamientos de " +
                                "inseguridad aunque lo haya trabajado e incluso lloro con facilidad"
                    )

                    Spacer(modifier = Modifier.height(65.dp))
                    TestScreen.CustomProgressBar(progress = 0.35f)

                }


            }

        }
    }
}