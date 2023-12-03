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

class TestScreen11 {

    companion object {

        @Composable
        fun Screen11() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition31 by remember { mutableStateOf(0f) }
            var sliderPosition32 by remember { mutableStateOf(0f) }
            var sliderPosition33 by remember { mutableStateOf(0f) }

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
                        sliderPosition = sliderPosition31.toInt(),
                        onValueChange = {sliderPosition31 = it},
                        textValues = textValues,
                        labelText = "31. Cuando estoy haciendo un examen el corazón me late muy deprisa."
                    )

                    Spacer(modifier = Modifier.height(40.dp))


                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition32.toInt(),
                        onValueChange = {sliderPosition32 = it},
                        textValues = textValues,
                        labelText = "32. Al entrar en el aula donde se hace el examen me tiemblan las piernas."
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition33.toInt(),
                        onValueChange = {sliderPosition33 = it},
                        textValues = textValues,
                        labelText = "33. Me siento nervioso/a en las clases demasiado pequeñas."
                    )

                    Spacer(modifier = Modifier.height(65.dp))
                    TestScreen.CustomProgressBar(progress = 0.35f)

                }


            }

        }
    }
}