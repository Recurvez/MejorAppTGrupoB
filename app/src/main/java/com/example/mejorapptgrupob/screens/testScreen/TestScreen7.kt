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

class TestScreen7 {

    companion object {


        @Composable
        fun Screen7() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition19 by remember { mutableStateOf(0f) }
            var sliderPosition20 by remember { mutableStateOf(0f) }
            var sliderPosition21 by remember { mutableStateOf(0f) }

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
                        sliderPosition = sliderPosition19.toInt(),
                        onValueChange = {sliderPosition19 = it},
                        textValues = textValues,
                        labelText = "19. No puedo quedarme quieto/a mientras hago el examen (muevo los pies, el bolígrafo, " +
                                "miro alrededor, miro la hora…)."
                    )

                    Spacer(modifier = Modifier.height(20.dp))


                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition20.toInt(),
                        onValueChange = {sliderPosition20 = it},
                        textValues = textValues,
                        labelText = "20. Me pongo malo/a y doy excusas para no hacer un examen."
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition21.toInt(),
                        onValueChange = {sliderPosition21 = it},
                        textValues = textValues,
                        labelText = "21. Para mí supone una tranquilidad o alivio cuando por cualquier razón, se aplaza un " +
                                "examen."
                    )

                    Spacer(modifier = Modifier.height(35.dp))
                    TestScreen.CustomProgressBar(progress = 0.35f)

                }


            }

        }
    }
}