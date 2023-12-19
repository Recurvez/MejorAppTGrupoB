package com.example.mejorapptgrupob.screens.testScreen

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.internalDB.DBUtilities
import com.example.mejorapptgrupob.screens.firstScreen.FirstActivity

object SliderUtility {
    fun resetSliderValues(context: Context) {
        saveSliderValue(context, "sliderPosition", 0f)
        saveSliderValue(context, "sliderPosition2", 0f)
        saveSliderValue(context, "sliderPosition3", 0f)
        saveSliderValue(context, "sliderPosition4", 0f)
    }
}

class TestActivity : ComponentActivity() {
    private lateinit var preguntas: List<String>

    var savedSliderPosition1 by mutableStateOf(0f)
    var savedSliderPosition2 by mutableStateOf(0f)
    var savedSliderPosition3 by mutableStateOf(0f)
    var savedSliderPosition4 by mutableStateOf(0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bloqueo de orientación
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val inputStream = resources.openRawResource(R.raw.preguntasv1)
        preguntas = QuestionList.readCSV(inputStream, this)
        val dbUtilities = DBUtilities(resources.openRawResource(R.raw.preguntasv1),this)



        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen(this@TestActivity, preguntas)

                }
            }
        }
    }
}


object GlobalLists {
    val respuestasFisiologica = mutableListOf<Int>(0, 0, 0, 0, 0, 0, 0, 0, 0)
    val respuestasCognitiva = mutableListOf<Int>(0, 0, 0, 0, 0, 0, 0)
    val respuestasEvitacion = mutableListOf<Int>(0, 0, 0, 0)
}

@Composable
internal fun Screen(mContext: Context, preguntas: List<String>) {

    val mContext = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }

    val textValues = listOf(
        "Selecciona una Opción",
        "Nunca", "En Pocas Ocasiones",
        "Con Cierta Frecuencia",
        "A Menudo o Casi Siempre",
        "Siempre"
    )

    var sliderPosition by remember { mutableStateOf(loadSliderValue(mContext, "sliderPosition")) }
    var sliderPosition2 by remember { mutableStateOf(loadSliderValue(mContext, "sliderPosition2")) }
    var sliderPosition3 by remember { mutableStateOf(loadSliderValue(mContext, "sliderPosition3")) }
    var sliderPosition4 by remember { mutableStateOf(loadSliderValue(mContext, "sliderPosition4")) }




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
    CustomProgressBar(progress = 0.0f)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Top,
            contentPadding = PaddingValues(top = 20.dp, bottom = 60.dp)
        ) {

            item {
                SliderWithValueText(
                    sliderPosition = sliderPosition.toInt(),
                    onValueChange = {
                        sliderPosition = it
                        saveSliderValue(mContext, "sliderPosition", it)
                    },
                    textValues = textValues,
                    labelText = preguntas.getOrNull(0) ?: ""
                )

                Spacer(modifier = Modifier.height(20.dp))

                SliderWithValueText(
                    sliderPosition = sliderPosition2.toInt(),
                    onValueChange = { sliderPosition2 = it
                        saveSliderValue(mContext, "sliderPosition2", it)},
                    textValues = textValues,
                    labelText = preguntas.getOrNull(1) ?: ""
                )

                Spacer(modifier = Modifier.height(20.dp))

                SliderWithValueText(
                    sliderPosition = sliderPosition3.toInt(),
                    onValueChange = { sliderPosition3 = it
                        saveSliderValue(mContext, "sliderPosition3", it)},
                    textValues = textValues,
                    labelText = preguntas.getOrNull(2) ?: ""
                )

                Spacer(modifier = Modifier.height(30.dp))

                SliderWithValueText(
                    sliderPosition = sliderPosition4.toInt(),
                    onValueChange = { sliderPosition4 = it
                        saveSliderValue(mContext, "sliderPosition4", it)},
                    textValues = textValues,
                    labelText = preguntas.getOrNull(3) ?: ""
                )

                Spacer(modifier = Modifier.height(30.dp))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BotonAtras(onClick = {
                        mContext.startActivity(Intent(mContext, FirstActivity::class.java))
                    })
                    BotonSiguiente(onClick = {
                        if (sliderPosition != 0f && sliderPosition2 != 0f &&
                            sliderPosition3 != 0f && sliderPosition4 != 0f
                        ) {
                            GlobalLists.respuestasFisiologica[0] = mapearValor(sliderPosition)
                            GlobalLists.respuestasCognitiva[0] = mapearValor(sliderPosition2)
                            GlobalLists.respuestasFisiologica[1] = mapearValor(sliderPosition3)
                            GlobalLists.respuestasCognitiva[1] = mapearValor(sliderPosition4)

                            // Todos los sliders tienen respuestas válidas, navegar a la siguiente pantalla
                            mContext.startActivity(Intent(mContext, TestActivity2::class.java))


                        } else {
                            openDialog.value = true
                        }
                    })
                    AlertDialogExample(openDialog)
                }
            }
        }
    }
}




internal fun loadSliderValue(context: Context, key: String): Float {
    val sharedPreferences = context.getSharedPreferences("slider_values", Context.MODE_PRIVATE)
    return sharedPreferences.getFloat(key, 0f)
}

internal fun saveSliderValue(context: Context, key: String, value: Float) {
    val sharedPreferences = context.getSharedPreferences("slider_values", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putFloat(key, value)
    editor.apply()
}
internal fun mapearValor(valorSlider: Float): Int {
    return when (valorSlider.toInt()) {
        1 -> 0 // "Nunca"
        2 -> 1 // "En Pocas Ocasiones"
        3 -> 2 // "Con Cierta Frecuencia"
        4 -> 3 // "A Menudo o Casi Siempre"
        5 -> 4 // "Siempre"
        else -> 0 // Por defecto, para "Selecciona una opción"
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
    val barColor = if (progress > 0f) Color(0xFF21379E) else Color.LightGray

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 10.dp),


        ) {
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



@Composable
fun BotonSiguiente(onClick: () -> Unit) {
    val customColor = Color(0xFF21379E)
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(customColor),
        modifier = Modifier

    ) {
        Text(text = "Siguiente")
    }
}


@Composable
fun BotonAtras(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier

    ) {
        Text(text = "Anterior",
            color = Color.White)
    }
}

internal fun showAlertDialog(context: Context) {
    AlertDialog.Builder(context)
        .setTitle("Advertencia")
        .setMessage("Debes responder a todas las preguntas.")
        .setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}

@Composable
internal fun AlertDialogExample(openDialog: MutableState<Boolean>) {
    val customColor = Color(0xFF21379E)

    if (openDialog.value) {
        AlertDialog(
            shape = RoundedCornerShape(15.dp),
            onDismissRequest = { openDialog.value = false },
            text = { Text(text = "Debes responder a todas las preguntas.") },
            title = { Text(text = "Advertencia") },
            confirmButton = {
                Button(
                    onClick = { openDialog.value = false },
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(customColor, contentColor = Color.White)
                ) {
                    Text(
                        text = "Aceptar",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                        color = Color.White
                    )
                }
            }
        )
    }
}





