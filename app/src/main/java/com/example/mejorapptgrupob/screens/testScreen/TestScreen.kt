package com.example.mejorapptgrupob.screens.testScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mejorapptgrupob.R

class TestScreen {

    companion object{

        @Composable
        fun Screen() {

            val textValues = listOf(
                "Selecciona una Opción",
                "Nunca", "En Pocas Ocasiones",
                "Con Cierta Frecuencia",
                "A Menudo o Casi Siempre",
                "Siempre"
            )

            var sliderPosition by remember { mutableStateOf(0f) }
            var sliderPosition2 by remember { mutableStateOf(0f) }
            var sliderPosition3 by remember { mutableStateOf(0f) }

            Image(
                painter = painterResource(id = R.drawable.testscreen2_bg),
                contentDescription = "background for test screen",
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.FillBounds
            )

            /*Box(
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
            */

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

                    SliderWithValueText(
                        sliderPosition = sliderPosition.toInt(),
                        onValueChange = {sliderPosition = it},
                        textValues = textValues,
                        labelText = "1. En los exámenes me sudan las manos"
                    )

                    Spacer(modifier = Modifier.height(30.dp))


                    SliderWithValueText(
                        sliderPosition = sliderPosition2.toInt(),
                        onValueChange = {sliderPosition2 = it},
                        textValues = textValues,
                        labelText = "2. Cuando llevo rato haciendo el examen, siento molestias en el estómago y necesidad de defecar."
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    SliderWithValueText(
                        sliderPosition = sliderPosition3.toInt(),
                        onValueChange = {sliderPosition3 = it},
                        textValues = textValues,
                        labelText = "3. Al comenzar a leer el examen se me nubla la vista y no entiendo lo que leo."
                    )

                    Spacer(modifier = Modifier.height(60.dp))
                    //CustomProgressBar(progress = 0.01f)

                }


            }

        }

        @Composable
        internal fun SliderWithValueText(
            sliderPosition: Int,
            onValueChange: (Float) -> Unit,
            textValues: List<String>,
            labelText: String
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF9F21379E), // Color inicial del gradiente
                                Color(0xFF21379E)    // Color final del gradiente
                            )
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = labelText, // Texto personalizado
                        Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .padding(12.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                    Slider(
                        value = sliderPosition.toFloat(),
                        onValueChange = onValueChange,
                        valueRange = 0f..(textValues.size - 1).toFloat(),
                        steps = 5,
                        modifier = Modifier.fillMaxWidth(),
                        colors = SliderDefaults.colors(
                            inactiveTrackColor = Color.White,
                            thumbColor = Color.Black
                        )
                    )
                    Text(
                        text = textValues[sliderPosition],
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }



        @Composable
        internal fun CustomProgressBar(progress: Float) {
            val barColor = if (progress > 0f) Color(0xFF573F60) else Color.LightGray

            Box(
                modifier = Modifier
                    .width(310.dp)
                    .height(10.dp)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(10.dp)
                        .background(
                            color = barColor,
                            shape = RoundedCornerShape(4.dp)
                        )

                )
            }

        }
    }
}