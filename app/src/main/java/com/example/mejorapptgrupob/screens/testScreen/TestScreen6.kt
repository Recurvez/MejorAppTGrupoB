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

class TestScreen6 {

    companion object {


        @Composable
        fun Screen6() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition16 by remember { mutableStateOf(0f) }
            var sliderPosition17 by remember { mutableStateOf(0f) }
            var sliderPosition18 by remember { mutableStateOf(0f) }

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
                    .padding(top = 40.dp),

                contentAlignment = Alignment.Center
            ){
                Column (
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .align(Alignment.TopCenter)
                ){

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition16.toInt(),
                        onValueChange = {sliderPosition16 = it},
                        textValues = textValues,
                        labelText = "16. Pienso que el profesor/a me está observando constantemente"
                    )

                    Spacer(modifier = Modifier.height(30.dp))


                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition17.toInt(),
                        onValueChange = {sliderPosition17 = it},
                        textValues = textValues,
                        labelText = "17. Suelo morderme las uñas o el bolígrafo durante los exámenes"
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition18.toInt(),
                        onValueChange = {sliderPosition18 = it},
                        textValues = textValues,
                        labelText = "18. Tengo muchas ganas de ir al cuarto de baño durante el examen"
                    )

                    Spacer(modifier = Modifier.height(75.dp))
                    TestScreen.CustomProgressBar(progress = 0.35f)

                }


            }

        }
    }
}