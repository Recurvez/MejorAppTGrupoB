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

class TestScreen5 {

    companion object {


        @Composable
        fun Screen5() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition13 by remember { mutableStateOf(0f) }
            var sliderPosition14 by remember { mutableStateOf(0f) }
            var sliderPosition15 by remember { mutableStateOf(0f) }

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
                        sliderPosition = sliderPosition13.toInt(),
                        onValueChange = {sliderPosition13 = it},
                        textValues = textValues,
                        labelText = "13. Después del examen lloro con facilidad, al pensar lo mal que lo he hecho aunque no " +
                                "sepa el resultado"
                    )

                    Spacer(modifier = Modifier.height(20.dp))


                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition14.toInt(),
                        onValueChange = {sliderPosition14 = it},
                        textValues = textValues,
                        labelText = "14. Mientras que estoy realizando el examen, pienso que lo estoy haciendo muy mal."
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition15.toInt(),
                        onValueChange = {sliderPosition15 = it},
                        textValues = textValues,
                        labelText = "15. Me siento nervioso/a si los demás comienzan a entregar el examen antes que yo."
                    )

                    Spacer(modifier = Modifier.height(50.dp))
                    TestScreen.CustomProgressBar(progress = 0.35f)

                }


            }

        }
    }
}