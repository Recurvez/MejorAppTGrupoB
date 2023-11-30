package com.example.mejorapptgrupob.screens.registerScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mejorapptgrupob.R
class RegisterScreen {

    companion object{

        @Composable
        internal fun RegisterLayout() {
            val context = LocalContext.current

            Image(
                painter = painterResource(id = R.drawable.registerscreen_bg),
                contentDescription = "background for login screen",
                alignment = Alignment.BottomCenter,
                // El fondo no se llegaba a ajustar bien a la anchura del dispositivo
                contentScale = ContentScale.FillBounds
            )

            Column {
                Column(
                    Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp)
                ) {
                    Row {
                        Image(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Flecha para volver atrás",
                            colorFilter = ColorFilter.tint(Color.White) // TODO --> RECORDAR CAMBIAR LOS COLORES
                        )
                        Text(
                            text = "Atrás",
                            color = Color.White
                        )
                    }
                    Row {
                        Text(
                            text = "Registro",
                            color = Color.White,
                            fontSize = 30.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Icon
                    Row(
                        Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    )
                    {
                        Image(
                            painter = painterResource(
                                id = R.drawable.logo
                            ),
                            contentDescription = "Logo en la pantalla de inicio de sesión",
                            modifier = Modifier.size(180.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(start = 60.dp, end = 60.dp)
                    )
                    {

                        var isNameFocus by remember {
                            mutableStateOf(false)
                        }
                        var name by remember { mutableStateOf("") }
                        var isNameError by remember { mutableStateOf(false) }
                        val regex = Regex("""[^!@#\$%^&*()_+\-=\[\]{};':",./<>?\\|]+""")
                        isNameError = !name.matches(regex) && name != ""
                        Card(
                            shape = RoundedCornerShape(40.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            border = if (isNameFocus && !isNameError){
                                BorderStroke(2.dp, Color.Blue)
                            } else if(isNameFocus && isNameError){
                                BorderStroke(2.dp, Color.Red)
                            }
                            else {
                                BorderStroke(0.dp, Color.Transparent)
                            }
                        ) {
                            TextField(
                                value = name,
                                onValueChange = { name = it },
                                shape = RoundedCornerShape(40.dp),
                                placeholder = { Text(text = "Nombre de usuario") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Person,
                                        contentDescription = ""
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedContainerColor = Color.White, // TODO --> CAMBIAR  COLORES
                                    focusedContainerColor = Color.White, // TODO --> CAMBIAR  COLORES
                                ),
                                modifier = Modifier
                                    .width(280.dp)
                                    .onFocusChanged {
                                        // Se asigna el valor de isFocused(true or false) a la variable isFocus
                                        isNameFocus = it.isFocused
                                    }
                            )
                        }

                        if(isNameError){

                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Caracteres prohibidos",
                                color = Color.Red,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(start = 15.dp)
                                    .clickable(enabled = true) {
                                        generateToast(
                                            context,
                                            "Caracteres prohibidos\n" + """!@#\\\$%^&*()_+-=[]{};':\\\",./<>?\\\\|""",
                                            Toast.LENGTH_SHORT
                                        )
                                    },
                                )
                            Spacer(modifier = Modifier.padding(10.dp))
                        } else {
                            Spacer(modifier = Modifier.padding(20.dp))
                        }

                        var isAgeFocus by remember {
                            mutableStateOf(false)
                        }
                        Card(
                            shape = RoundedCornerShape(40.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            border = if (isAgeFocus) BorderStroke(2.dp, Color.Blue) else {
                                BorderStroke(0.dp, Color.Transparent)
                            }
                        )
                        {
                            var age by remember { mutableStateOf("Introduzca la edad") }
                            var isOpenDialog by remember { mutableStateOf(false) }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp)
                                    .background(Color.White)
                                    .clickable { isOpenDialog = true }
                                    .padding(start = 15.dp, end = 15.dp)

                            ) {
                                Icon(
                                    imageVector = Icons.Filled.DateRange,
                                    contentDescription = "Icono fecha nacimiento"
                                )
                                Row(
                                    Modifier.fillMaxWidth(),

                                ) {

                                    Text(
                                        text = age,
                                        modifier = Modifier.padding(start = 15.dp)
                                    )
                                    if(age != "Introduzca la edad" && age != ""){
                                        Text(
                                            text = " años",
                                        )
                                    }
                                }

                            }

                            when{
                                isOpenDialog -> {
                                    val (obtainedStatusDialog, newAge) = CardAge()
                                    isOpenDialog =  obtainedStatusDialog as Boolean
                                    age = newAge as String
                                }
                            }

                        }

                        Spacer(modifier = Modifier.padding(20.dp))

                        var isPasswordFocus by remember {
                            mutableStateOf(false)
                        }
                        Card(
                            shape = RoundedCornerShape(40.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            border = if (isPasswordFocus) BorderStroke(2.dp, Color.Blue) else {
                                BorderStroke(0.dp, Color.Transparent)
                            }
                        )
                        {
                            TextField(
                                value = "",
                                onValueChange = { },
                                shape = RoundedCornerShape(40.dp),
                                placeholder = { Text(text = "Introduzca una contraseña") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Lock,
                                        contentDescription = ""
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedContainerColor = Color.White, // TODO --> CAMBIAR  COLORES
                                    focusedContainerColor = Color.White, // TODO --> CAMBIAR  COLORES
                                ),
                                modifier = Modifier
                                    .width(280.dp)
                                    .onFocusChanged {
                                        // Se asigna el valor de isFocused(true or false) a la variable isFocus
                                        isPasswordFocus = it.isFocused
                                    }
                            )
                        }

                        Spacer(modifier = Modifier.padding(20.dp))

                        var isRepeatPasswordFocus by remember {
                            mutableStateOf(false)
                        }
                        Card(
                            shape = RoundedCornerShape(40.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            border = if (isRepeatPasswordFocus) BorderStroke(2.dp, Color.Blue) else {
                                BorderStroke(0.dp, Color.Transparent)
                            }
                        )
                        {
                            TextField(
                                value = "",
                                onValueChange = { },
                                shape = RoundedCornerShape(40.dp),
                                placeholder = { Text(text = "Repita la contraseña") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Lock,
                                        contentDescription = ""
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedContainerColor = Color.White, // TODO --> CAMBIAR  COLORES
                                    focusedContainerColor = Color.White, // TODO --> CAMBIAR  COLORES
                                ),
                                modifier = Modifier
                                    .width(280.dp)
                                    .onFocusChanged {
                                        // Se asigna el valor de isFocused(true or false) a la variable isFocus
                                        isRepeatPasswordFocus = it.isFocused
                                    }
                            )
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        Row(Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            ElevatedButton(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White, // TODO --> CAMBIAR  COLORES
                                    contentColor = Color.Magenta // TODO --> CAMBIAR  COLORES
                                ),
                                onClick = { /*TODO*/ }
                            ) {
                                Text(text = "Registrarme")
                            }
                        }


                    }

                }

            }

        }

    }
}