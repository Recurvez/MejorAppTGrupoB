package com.example.mejorapptgrupob.screens.loginScreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mejorapptgrupob.DataStoreManager
import com.example.mejorapptgrupob.MainActivity
import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.screens.firstScreen.FirstActivity
import com.example.mejorapptgrupob.screens.loginScreen.rememberImeState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

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
                    val dataStore = DataStoreManager.dataStore
                    LoginLayout(dataStore = dataStore)
                }
            }
        }
    }
}

val USER_KEY = stringPreferencesKey("user_key")
val PASSWORD_KEY = stringPreferencesKey("password_key")

private suspend fun guardarPreferencias(dataStore: DataStore<Preferences>, username: String, password: String) {
    dataStore.edit { preferences ->
        preferences[USER_KEY] = username
        preferences[PASSWORD_KEY] = password
    }
}

suspend fun mostrarPreferencias(dataStore: DataStore<Preferences>) {
    val preferences = dataStore.data.first()

    val username = preferences[USER_KEY]
    val password = preferences[PASSWORD_KEY]

    println("Nombre de usuario: $username")
    println("Contraseña: $password")
}


@Composable
internal fun LoginLayout(dataStore: DataStore<Preferences>){

    var mcontext = LocalContext.current

    // viewModel donde trataremos el login del usuario
    val viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    // https://www.youtube.com/watch?v=kgoJfl_Oc5E
    val imeState by rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState){
        if(imeState){
            scrollState.scrollTo(scrollState.value)
        }
    }

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val preferences = dataStore.data.first()
        username = preferences[USER_KEY] ?: ""
        password = preferences[PASSWORD_KEY] ?: ""
    }
    val coroutineScope = rememberCoroutineScope()

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
        Column(
            Modifier
                .padding(start = 10.dp, top = 5.dp)
                .verticalScroll(scrollState)

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

        Spacer(modifier = Modifier.height(60.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .height(40.dp), horizontalArrangement = Arrangement.Center) {
            if (viewModel.inProgress.value) {
                CircularProgressIndicator()
            }
        }


        Spacer(modifier = Modifier.height(70.dp))

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
                    value = username,
                    onValueChange = { username = it  },
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
                    value = password,
                    onValueChange = { password = it },
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

            var userCanRegister by remember { mutableStateOf(false) }
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White, // TODO --> CAMBIAR  COLORES
                    contentColor = Color.Magenta // TODO --> CAMBIAR  COLORES
                ),
                onClick = {
                    coroutineScope.launch {
                        guardarPreferencias(dataStore, username, password)
                        mostrarPreferencias(dataStore)

                        // Para el acceso mediante Firebase
                        viewModel.signInWithEmailAndPassword(email = username, password = password){
                            userCanRegister = true
                        }

                    }
                },
            ) {
                Text(text = "Iniciar sesión")
            }

            if(userCanRegister){
                mcontext.startActivity(Intent(mcontext, FirstActivity::class.java))
            }

        }
    }
}