package com.example.mejorapptgrupob.screens.mainScreen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.screens.loginScreen.LoginScreen
import com.example.mejorapptgrupob.screens.registerScreen.RegisterScreen

class MainScreen {
    companion object {

        @Composable
        @JvmStatic
        fun Main(userDataStore: DataStore<Preferences>, navigateToLogin: () -> Unit, navigateToRegister: () -> Unit){
            val isLoggedIn = remember { mutableStateOf(false) }

            if (isLoggedIn.value) {
                Text("¡Bienvenido!")
            } else {
                MainLayout(
                    onLoginClick = navigateToLogin,
                    onRegisterClick = navigateToRegister
                )
            }
        }
        @Composable
        internal fun MainLayout(onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {

            // Creada fuera de la columna principal, al estar a nivel de surface
            // coge el tamaño automáticamente
            Image(
                painter = painterResource(id = R.drawable.mainscreen_bg),
                contentDescription = "background for main screen",
                alignment = Alignment.BottomCenter,
            )

            // Tengo que crear una columna dentro de otra por la surface de
            // la mainActivity, ya que coge el máximo tamaño de cualquier componente
            Column {
                Column(
                    Modifier
                        .height(230.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                        // .background(Color.Black)

                    )
                    Text(
                        text = "APP SALUD MENTAL",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Magenta // TODO -> CAMBIAR ESTO CUANDO SE TENGA LOS COLORES PREPARADOS
                    )

                    Text(
                        text = "Se feliz y eso",
                        color = Color.Magenta
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(360.dp)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = onLoginClick,
                        modifier = Modifier.width(140.dp)
                    ) {
                        Text(text = "Iniciar sesión")
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    OutlinedButton(
                        onClick = onRegisterClick,
                        modifier = Modifier.width(140.dp)
                    ) {
                        Text(text = "Registrarse")
                    }
                }
            }
        }

    }

}