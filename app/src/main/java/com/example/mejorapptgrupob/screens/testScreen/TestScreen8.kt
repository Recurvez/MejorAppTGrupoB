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

class TestScreen8 {

    companion object {


        @Composable
        fun Screen8() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition22 by remember { mutableStateOf(0f) }
            var sliderPosition23 by remember { mutableStateOf(0f) }
            var sliderPosition24 by remember { mutableStateOf(0f) }

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
                        sliderPosition = sliderPosition22.toInt(),
                        onValueChange = {sliderPosition22 = it},
                        textValues = textValues,
                        labelText = "22. Pienso que no voy a poder aprobar el examen, aunque haya estudiado."
                    )

                    Spacer(modifier = Modifier.height(30.dp))


                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition23.toInt(),
                        onValueChange = {sliderPosition23 = it},
                        textValues = textValues,
                        labelText = "23. Antes de hacer el examen pienso que no me acuerdo de nada y voy a suspenderlo."
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition24.toInt(),
                        onValueChange = {sliderPosition24 = it},
                        textValues = textValues,
                        labelText = "24. No consigo dormir la noche anterior a un examen."
                    )

                    Spacer(modifier = Modifier.height(60.dp))
                    TestScreen.CustomProgressBar(progress = 0.35f)

                }


            }

        }
    }
}