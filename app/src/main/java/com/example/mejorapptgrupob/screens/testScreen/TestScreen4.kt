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

class TestScreen4 {


        companion object {


            @Composable
            fun Screen4() {

                val textValues = listOf(
                    "Selecciona una Opción",
                    "Nunca", "En Pocas Ocasiones",
                    "Con Cierta Frecuencia",
                    "A Menudo o Casi Siempre",
                    "Siempre"
                )

                var sliderPosition10 by remember { mutableStateOf(0f) }
                var sliderPosition11 by remember { mutableStateOf(0f) }
                var sliderPosition12 by remember { mutableStateOf(0f) }

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
                            sliderPosition = sliderPosition10.toInt(),
                            onValueChange = {sliderPosition10 = it},
                            textValues = textValues,
                            labelText = "10. En el examen siento rígidas las manos y/o los brazos."
                        )

                        Spacer(modifier = Modifier.height(20.dp))


                        TestScreen.SliderWithValueText(
                            sliderPosition = sliderPosition11.toInt(),
                            onValueChange = {sliderPosition11 = it},
                            textValues = textValues,
                            labelText = "11. Antes de entrar al examen siento un nudo en el estómago, que desaparece al" +
                                    "comenzar a escribir"
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        TestScreen.SliderWithValueText(
                            sliderPosition = sliderPosition12.toInt(),
                            onValueChange = {sliderPosition12 = it},
                            textValues = textValues,
                            labelText = "12. Al comenzar el examen, nada más leer las preguntas lo entrego al profesor/a en " +
                                    "blanco y vuelvo a mi sitio"
                        )

                        Spacer(modifier = Modifier.height(45.dp))
                        TestScreen.CustomProgressBar(progress = 0.35f)

                    }


                }

            }
        }
}