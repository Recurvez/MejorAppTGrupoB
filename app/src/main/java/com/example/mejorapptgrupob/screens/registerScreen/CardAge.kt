package com.example.mejorapptgrupob.screens.registerScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.mejorapptgrupob.R
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
/* Va a devolver el estado de isClicked y la edad */
internal fun CardAge(): List<Any>{
    val contextForToast = LocalContext.current.applicationContext

    var isOpenDialog by remember { mutableStateOf(true) }
    var age by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { isOpenDialog = true },
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) {
            var sliderValue by remember {
                mutableStateOf(10f)
            }
            var value = 0
            Column(Modifier.weight(9f)) {
                Row() {
                    Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        Slider(
                            modifier = Modifier
                                .graphicsLayer {
                                    rotationZ = 270f
                                    transformOrigin = TransformOrigin(0f, 0f)
                                }
                                .layout { measurable, constraints ->
                                    val placeable = measurable.measure(
                                        Constraints(
                                            minWidth = constraints.minHeight,
                                            maxWidth = constraints.maxHeight,
                                            minHeight = constraints.minWidth,
                                            maxHeight = constraints.maxHeight,
                                        )
                                    )
                                    layout(placeable.height, placeable.width) {
                                        placeable.place(-placeable.width, 0)
                                    }
                                }
                                .padding(end = 10.dp)
                                .width(500.dp)
                                .height(80.dp),
                            value = sliderValue,
                            onValueChange = { newValue ->
                                sliderValue = newValue
                            },
                            onValueChangeFinished = {
                                /*
                                Toast.makeText(contextForToast, "sliderValue = $sliderValue", Toast.LENGTH_SHORT)
                                    .show()

                                 */
                            },
                            valueRange = 10f..60f,
                            // steps = 10
                        )
                        value = (sliderValue).roundToInt()
                        /*
                        BasicTextField(
                            value = value.toString(),
                            onValueChange = { value = it.toInt()},
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                        )
                        */
                        Text(
                            text = "$value",


                        )
                        sliderValue = value.toFloat()
                    }
                    Column(
                        Modifier
                            .weight(5f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.Start
                    ) {
                        if(value <= 16 )
                            Image(painter = painterResource(id = R.drawable.child), contentDescription = "child image", modifier = Modifier.padding(start = 50.dp, bottom = 30.dp))
                        else if(value < 30)
                            Image(painter = painterResource(id = R.drawable.men), contentDescription = "a men", modifier = Modifier.padding(end = 40.dp,bottom = 20.dp))
                        else if(value in 30..60)
                            Image(painter = painterResource(id = R.drawable.man_working), contentDescription = "man working", modifier = Modifier.padding(start = 40.dp,bottom = 20.dp))
                    }

                }
            }
            Column(Modifier.weight(1f)) {
                Spacer(modifier = Modifier.height(10.dp))
                Divider(modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable {
                            isOpenDialog = false
                            age = value.toString()
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Continuar",
                        color = MaterialTheme.colorScheme.primary
                    )

                }
            }


        }
    }
    return listOf(isOpenDialog, age)
}