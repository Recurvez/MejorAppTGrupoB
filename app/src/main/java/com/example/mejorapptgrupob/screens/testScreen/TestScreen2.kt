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

class TestScreen2 {

    companion object {


        @Composable
        fun Screen2() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition4 by remember { mutableStateOf(0f) }
            var sliderPosition5 by remember { mutableStateOf(0f) }
            var sliderPosition6 by remember { mutableStateOf(0f) }

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
                        sliderPosition = sliderPosition4.toInt(),
                        onValueChange = {sliderPosition4 = it},
                        textValues = textValues,
                        labelText = "4. Si llego 5 minutos tarde a un examen ya no entro, me escondo por el instituto o " +
                                "intento marcharme."
                    )

                    Spacer(modifier = Modifier.height(20.dp))


                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition5.toInt(),
                        onValueChange = {sliderPosition5 = it},
                        textValues = textValues,
                        labelText = "5. Las condiciones donde se realiza un examen (mucho ruido, calor, frío, sol…) me " +
                                "influyen aumentando mi nerviosismo."
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition6.toInt(),
                        onValueChange = {sliderPosition6 = it},
                        textValues = textValues,
                        labelText = "6. Cuando termino un examen me duele la cabeza."
                    )

                    Spacer(modifier = Modifier.height(35.dp))
                    TestScreen.CustomProgressBar(progress = 0.35f)

                }


            }

        }
    }
}