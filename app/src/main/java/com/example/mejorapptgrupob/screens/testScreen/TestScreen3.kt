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

class TestScreen3 {

    companion object {


        @Composable
        fun Screen3() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition7 by remember { mutableStateOf(0f) }
            var sliderPosition8 by remember { mutableStateOf(0f) }
            var sliderPosition9 by remember { mutableStateOf(0f) }

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
                    .padding(top = 10.dp),

                contentAlignment = Alignment.Center
            ){
                Column (
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .align(Alignment.TopCenter)
                ){

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition7.toInt(),
                        onValueChange = {sliderPosition7 = it},
                        textValues = textValues,
                        labelText = "7. Cuando llevo un rato haciendo el examen, yo siento que me falta el aire, mucho calor y sensación de que me voy a desmayar."
                    )

                    Spacer(modifier = Modifier.height(20.dp))


                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition8.toInt(),
                        onValueChange = {sliderPosition8 = it},
                        textValues = textValues,
                        labelText = "8. Me siento nervioso/a si el profesor/a se para junto a mí y ya no puedo seguir contestando"
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TestScreen.SliderWithValueText(
                        sliderPosition = sliderPosition9.toInt(),
                        onValueChange = {sliderPosition9 = it},
                        textValues = textValues,
                        labelText = "9. Me pongo nervioso/a al ver al profesor/a con los exámenes antes de entrar al aula"
                    )

                    Spacer(modifier = Modifier.height(25.dp))
                    TestScreen.CustomProgressBar(progress = 0.35f)

                }


            }

        }
    }
}