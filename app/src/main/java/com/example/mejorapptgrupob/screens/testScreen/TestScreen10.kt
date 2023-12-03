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

class TestScreen10 {

    companion object {


        @Composable
        fun Screen10() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition28 by remember { mutableStateOf(0f) }
            var sliderPosition29 by remember { mutableStateOf(0f) }
            var sliderPosition30 by remember { mutableStateOf(0f) }

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
                        sliderPosition = sliderPosition28.toInt(),
                        onValueChange = {sliderPosition28 = it},
                        textValues = textValues,
                        labelText = "28. Si me siento en las primeras filas para hacer el examen, aumenta mi nerviosismo."
                    )

                    Spacer(modifier = Modifier.height(30.dp))


                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition29.toInt(),
                        onValueChange = {sliderPosition29 = it},
                        textValues = textValues,
                        labelText = "29. Si el examen tiene un tiempo fijo para realizarse, aumenta mi nerviosismo y lo hago" +
                                "peor"
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition30.toInt(),
                        onValueChange = {sliderPosition30 = it},
                        textValues = textValues,
                        labelText = "30. Me siento nervioso/a en aulas muy grandes."
                    )

                    Spacer(modifier = Modifier.height(45.dp))
                    TestScreen.CustomProgressBar(progress = 0.35f)

                }

            }

        }
    }
}