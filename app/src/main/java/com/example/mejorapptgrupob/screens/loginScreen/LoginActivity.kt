package com.example.mejorapptgrupob.screens.loginScreen

import ResultsViewModel
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.mejorapptgrupob.DataStoreManager
import com.example.mejorapptgrupob.MainActivity
import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.screens.firstScreen.FirstActivity
import com.example.mejorapptgrupob.screens.registerScreen.rememberImeState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

var currentUser = ""
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bloqueo de orientación
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginLayout(dataStore = DataStoreManager.dataStore)
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


@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun LoginLayout(dataStore: DataStore<Preferences>){
    val dataStore = DataStoreManager.dataStore

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
    var isKeyboardVisible by remember { mutableStateOf(false) }

    LaunchedEffect(imeState) {
        isKeyboardVisible = imeState
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

    var isPasswordFocus by remember {
        mutableStateOf(false)
    }

    var isUserFocus by remember {
        mutableStateOf(false)
    }


    Column {
        Column(
            Modifier
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

        Spacer(modifier = Modifier.height(if (isKeyboardVisible) 0.dp else 60.dp))


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


        Spacer(modifier = Modifier.height(if (isKeyboardVisible) 0.dp else 60.dp))


        Row(
            Modifier
                .fillMaxWidth()
                .height(20.dp), horizontalArrangement = Arrangement.Center) {
            if (viewModel.inProgress.value) {
                CircularProgressIndicator()
            }
        }



        Spacer(modifier = Modifier.height(if (isKeyboardVisible) 0.dp else 60.dp))

        var isEmailBlankClick by remember{ mutableStateOf(false) }

        Row(
            Modifier
                .height(20.dp)
                .padding(start = 75.dp)) {
            if (isEmailBlankClick){
                Text(text = "Indique un correo", color = Color.Red)
            }
        }


        Column(
            Modifier
                .fillMaxSize()
                .padding(end = 30.dp, bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            /* https://stackoverflow.com/questions/64363502/how-to-remove-indicator-line-of-textfield-in-androidx-compose-material */


            Card(
                shape = RoundedCornerShape(40.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                border = if (isUserFocus) BorderStroke(2.dp, Color.Blue) else if(isEmailBlankClick) {BorderStroke(2.dp, Color.Red)} else { BorderStroke(0.dp, Color.Transparent) }
            ) {
                TextField(
                    value = username,
                    onValueChange = { username = it  },
                    shape = RoundedCornerShape(40.dp),
                    placeholder = {Text(text ="Correo")},
                    leadingIcon = { Icon(imageVector = Icons.Filled.Mail, contentDescription = "") },
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


            Spacer(modifier = Modifier.height(30.dp))


            var passwordState by remember { mutableIntStateOf(-2) }


            var isPasswordBlankClick by remember { mutableStateOf(false) }

            if(isPasswordFocus) {
                passwordState = 1
            } else if(!isPasswordFocus && !isPasswordBlankClick){
                passwordState = 0
            }

            var borderColor: BorderStroke =
                if(isPasswordBlankClick) BorderStroke(2.dp, Color.Red)
                else if(passwordState == 1) BorderStroke(2.dp, Color.Blue)
                else BorderStroke(0.dp, Color.Transparent)


            Card(
                shape = RoundedCornerShape(40.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                border = borderColor
            )
            {
                var isPasswordVisible by remember { mutableStateOf(false) }
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    shape = RoundedCornerShape(40.dp),
                    placeholder = { Text(text = "Contraseña") },
                    singleLine = true,
                    visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = ""
                        )
                    },
                    trailingIcon = {
                        val image = if (isPasswordVisible){
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }
                        val description = if (isPasswordVisible) "Hide password" else "Show password"

                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(imageVector = image, contentDescription = description)
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.White, // TODO --> CAMBIAR  COLORES
                        focusedContainerColor = Color.White, // TODO --> CAMBIAR  COLORES
                    ),
                    modifier = Modifier.onFocusChanged {
                        // Se asigna el valor de isFocused(true or false) a la variable isFocus
                        isPasswordFocus = it.isFocused

                    }
                )
            }

            val savedUsername = username;

            Spacer(modifier = Modifier.height(40.dp))




            var userCanLogin by remember { mutableStateOf(false) }
            var openDialogInvalidCredentials by remember { mutableStateOf(false) }
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White, // TODO --> CAMBIAR  COLORES
                    contentColor = Color.Magenta // TODO --> CAMBIAR  COLORES
                ),
                onClick = {
                    coroutineScope.launch {

                        // Comprobación de que los campos no esten vacíos para que no salte excepción
                        isEmailBlankClick = username.isBlank()
                        isPasswordBlankClick = password.isBlank()
                        if( !(isEmailBlankClick || isPasswordBlankClick) ){
                            // Para el acceso mediante Firebase
                            viewModel.signInWithEmailAndPassword(email = username, password = password,
                                sucessActions = {
                                    userCanLogin = true
                                    currentUser = savedUsername
                            },
                                failedActions = {
                                    openDialogInvalidCredentials = true
                                })
                        }
                    }
                },
            ) {
                Text(text = "Iniciar sesión")
            }


            if(userCanLogin){
                var intent =  Intent(mcontext, FirstActivity::class.java)

                // Borra las actividades anteriores y deja la first como la primera del stack
                // https://stackoverflow.com/questions/12947916/android-remove-all-the-previous-activities-from-the-back-stack
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                mcontext.startActivity(intent)
            }


            if(openDialogInvalidCredentials){
                var status = InvalidCredentialCard()
                openDialogInvalidCredentials = status
            }
        }
    }

}