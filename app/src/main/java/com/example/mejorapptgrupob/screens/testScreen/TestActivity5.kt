    package com.example.mejorapptgrupob.screens.testScreen

    import ResultsViewModel
    import android.content.Context
    import android.content.Intent
    import android.os.Bundle
    import android.util.Log
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.PaddingValues
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Surface
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.unit.dp
    import com.example.mejorapptgrupob.R
    import com.example.mejorapptgrupob.internalDB.DBUtilities
    import com.example.mejorapptgrupob.screens.adviceScreen.AdviceActivity
    import com.example.mejorapptgrupob.screens.loginScreen.LoginActivity
    import com.example.mejorapptgrupob.screens.loginScreen.currentUser

    object SliderUtility5 {
        fun resetSliderValues(context: Context) {
            saveSliderValue(context, "sliderPosition17", 0f)
            saveSliderValue(context, "sliderPosition18", 0f)
            saveSliderValue(context, "sliderPosition19", 0f)
            saveSliderValue(context, "sliderPosition20", 0f)
        }
    }
    val username = currentUser.value
    class TestActivity5 : ComponentActivity() {
        private lateinit var preguntas: List<String>



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val dbUtilities = DBUtilities(resources.openRawResource(R.raw.preguntas),this)
            val inputStream = resources.openRawResource(R.raw.preguntas)
            preguntas = QuestionList.readCSV(inputStream, this)
            setContent {
                MaterialTheme(
                    colorScheme = MaterialTheme.colorScheme
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Screen5(this@TestActivity5, preguntas)
                    }
                }
            }
        }
    }

    @Composable
    internal fun Screen5(mContext: Context, preguntas: List<String>) {
        val viewModel = ResultsViewModel()
        val respuestasTemporales = remember { mutableStateOf(listOf<Int>(0, 0, 0, 0)) }
        val respuestasTemporales2 = remember { mutableStateOf(listOf<Int>(0, 0, 0, 0)) }
        val respuestasTemporales3 = remember { mutableStateOf(listOf<Int>(0, 0, 0, 0)) }

        val mContext = LocalContext.current
        val openDialog = remember { mutableStateOf(false) }

        val textValues = listOf(
            "Selecciona una Opción",
            "Nunca", "En Pocas Ocasiones",
            "Con Cierta Frecuencia",
            "A Menudo o Casi Siempre",
            "Siempre"
        )

        var sliderPosition17 by remember { mutableStateOf(loadSliderValue(mContext, "sliderPosition17")) }
        var sliderPosition18 by remember { mutableStateOf(loadSliderValue(mContext, "sliderPosition18")) }
        var sliderPosition19 by remember { mutableStateOf(loadSliderValue(mContext, "sliderPosition19")) }
        var sliderPosition20 by remember { mutableStateOf(loadSliderValue(mContext, "sliderPosition20")) }


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
        CustomProgressBar(progress = 0.8f)
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
                        sliderPosition = sliderPosition17.toInt(),
                        onValueChange = { sliderPosition17 = it
                            saveSliderValue(mContext, "sliderPosition17", it)},
                        textValues = textValues,
                        labelText = preguntas.getOrNull(16) ?: ""
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    SliderWithValueText(
                        sliderPosition = sliderPosition18.toInt(),
                        onValueChange = { sliderPosition18 = it
                            saveSliderValue(mContext, "sliderPosition18", it)},
                        textValues = textValues,
                        labelText = preguntas.getOrNull(17) ?: ""
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    SliderWithValueText(
                        sliderPosition = sliderPosition19.toInt(),
                        onValueChange = { sliderPosition19 = it
                            saveSliderValue(mContext, "sliderPosition19", it)},
                        textValues = textValues,
                        labelText = preguntas.getOrNull(18) ?: ""
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    SliderWithValueText(
                        sliderPosition = sliderPosition20.toInt(),
                        onValueChange = { sliderPosition20 = it
                            saveSliderValue(mContext, "sliderPosition20", it)},
                        textValues = textValues,
                        labelText = preguntas.getOrNull(19) ?: ""
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
                            mContext.startActivity(Intent(mContext, TestActivity4::class.java))
                        })
                        BotonSiguiente(onClick = {
                            if (sliderPosition17 != 0f || sliderPosition18 != 0f ||
                                sliderPosition19 != 0f || sliderPosition20 != 0f
                            ) {
                                GlobalLists.respuestasFisiologica[7] = mapearValor(sliderPosition17)
                                GlobalLists.respuestasCognitiva[6] = mapearValor(sliderPosition18)
                                GlobalLists.respuestasEvitacion[3] = mapearValor(sliderPosition19)
                                GlobalLists.respuestasFisiologica[8] = mapearValor(sliderPosition20)


                                val sumC = GlobalLists.respuestasCognitiva.sum()
                                val sumF = GlobalLists.respuestasFisiologica.sum()
                                val sumE = GlobalLists.respuestasEvitacion.sum()

                                val nick = username
                                viewModel.saveResults(nick, sumC, sumF, sumE) { success ->
                                    if (success) {
                                        Log.d("TAG", "Resultados guardados exitosamente")
                                    } else {
                                        Log.e("TAG", "Error al guardar los resultados")
                                    }
                                }


                                Log.d("TAG", "Respuestas Fisiológicas: ${GlobalLists.respuestasFisiologica}")
                                Log.d("TAG", "Respuestas Cognitivas: ${GlobalLists.respuestasCognitiva}")
                                Log.d("TAG", "Respuestas Evitación: ${GlobalLists.respuestasEvitacion}")



                                mContext.startActivity(Intent(mContext, AdviceActivity::class.java))
                            } else {
                                openDialog.value = true
                            }
                        })
                    }
                }
            }
        }
    }