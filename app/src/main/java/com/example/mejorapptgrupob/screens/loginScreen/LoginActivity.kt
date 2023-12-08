package com.example.mejorapptgrupob.screens.loginScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mejorapptgrupob.MainActivity
import com.example.mejorapptgrupob.MainLayout
import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.screens.loginScreen.ui.theme.MejorAppTGrupoBTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginLayout()
                }
            }
        }
    }
}

@Composable
internal fun LoginLayout(){

    val mContext = LocalContext.current

    Image(
        painter = painterResource(id = R.drawable.loginscreen_bg) ,
        contentDescription = "background for login screen",
        alignment = Alignment.BottomCenter,
        contentScale = ContentScale.FillBounds
    )

    // TODO --> Problemas con el teclado, se overlapa y no deja
    // ver el campo contraseña
    // Poner también que cuando le des a la flecha pase del usuario
    // a la contraseña

    Column {
        Column(Modifier
            .padding(start = 10.dp, top = 5.dp)
        ) {
            Row(
                Modifier.clickable {
                    mContext.startActivity(Intent(mContext, MainActivity::class.java))
                }
            ) {
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
                    text = "Inicio de sesión",
                    color = Color.White,
                    fontSize = 30.sp
                )
            }
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
            Image(painter = painterResource(
                id = R.drawable.logo),
                contentDescription = "Logo en la pantalla de inicio de sesión",
                modifier = Modifier.size(180.dp)
            )
        }

        Spacer(modifier = Modifier.height(250.dp))

        Column(
            Modifier
                .fillMaxSize()
                .padding(end = 30.dp, bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            /* https://stackoverflow.com/questions/64363502/how-to-remove-indicator-line-of-textfield-in-androidx-compose-material */

            var isUserFocus by remember {
                mutableStateOf(false)
            }
            Card(
                shape = RoundedCornerShape(40.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                border = if (isUserFocus) BorderStroke(2.dp, Color.Blue) else { BorderStroke(0.dp, Color.Transparent) }
            ) {
                TextField(
                    value = "",
                    onValueChange = {  },
                    shape = RoundedCornerShape(40.dp),
                    placeholder = {Text(text ="Usuario")},
                    leadingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "") },
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.White, // TODO --> CAMBIAR  COLORES
                        focusedContainerColor = Color.White, // TODO --> CAMBIAR  COLORES
                    ),
                    modifier = Modifier.onFocusChanged {
                        // Se asigna el valor de isFocused(true or false) a la variable isFocus
                        isUserFocus = it.isFocused
                    }
                )
            }

            Spacer(modifier =Modifier.padding(20.dp))

            var isPasswordFocus by remember {
                mutableStateOf(false)
            }
            Card(
                shape = RoundedCornerShape(40.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                border = if (isPasswordFocus) BorderStroke(2.dp, Color.Blue) else { BorderStroke(0.dp, Color.Transparent) }
            ) {
                TextField(
                    value = "",
                    onValueChange = {  },
                    shape = RoundedCornerShape(40.dp),
                    placeholder = {Text(text ="Contraseña")},
                    leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "") },
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.White, // TODO --> CAMBIAR  COLORES
                        focusedContainerColor = Color.White // TODO --> CAMBIAR  COLORES
                    ),
                    modifier = Modifier.onFocusChanged {
                        // Se asigna el valor de isFocused(true or false) a la variable isFocus
                        isPasswordFocus = it.isFocused
                    }
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            ElevatedButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White, // TODO --> CAMBIAR  COLORES
                    contentColor = Color.Magenta // TODO --> CAMBIAR  COLORES
                ),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Iniciar sesión")
            }
        }
    }
}