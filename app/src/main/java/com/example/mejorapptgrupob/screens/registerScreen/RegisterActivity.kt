package com.example.mejorapptgrupob.screens.registerScreen

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.mejorapptgrupob.MainActivity
import com.example.mejorapptgrupob.R
import com.example.mejorapptgrupob.firebase.FirebaseUtils
import com.example.mejorapptgrupob.screens.firstScreen.FirstActivity
import com.example.mejorapptgrupob.screens.loginScreen.currentUser

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bloqueo de orientación
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterLayout()
                }
            }
        }
    }
}

@Composable
internal fun RegisterLayout() {

    val viewModel: RegisterScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel();

    val imeState by rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState){

        /*if(imeState){
            scrollState.scrollTo(scrollState.value)
        }

         */
    }


    val context = LocalContext.current

    Image(
        painter = painterResource(id = R.drawable.registerscreen_bg),
        contentDescription = "background for login screen",
        alignment = Alignment.BottomCenter,
        // El fondo no se llegaba a ajustar bien a la anchura del dispositivo
        contentScale = ContentScale.Crop
    )


    Column {
        Column(
            Modifier
                .padding(start = 10.dp, end = 10.dp, top = 5.dp)
                .verticalScroll(scrollState)
        ) {
            Row(
                Modifier.clickable {
                    var intent = Intent(context, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
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
                    text = "Registro",
                    color = Color.White,
                    fontSize = 30.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column() {
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
                        contentDescription = "Logo en la pantalla de registro",
                        modifier = Modifier.size(180.dp)
                    )
                }



                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 60.dp, end = 60.dp)
                )
                {

                    var isEmailFocus by remember { mutableStateOf(false) }
                    var email by remember { mutableStateOf("") }
                    var isEmailError by remember { mutableStateOf(false) }
                    // https://stackoverflow.com/questions/201323/how-can-i-validate-an-email-address-using-a-regular-expression
                    isEmailError = ! email.matches(Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))

                    var borderMail: BorderStroke =
                        if(!isEmailFocus && email == "") {
                            BorderStroke(0.dp, Color.Transparent)
                        } else if (isEmailFocus && email == ""){
                            BorderStroke(2.dp, Color.Blue)
                        } else if(isEmailError){
                            BorderStroke(2.dp, Color.Red)
                        } else {
                            BorderStroke(2.dp, Color.Green)
                        }

                    Card(
                        shape = RoundedCornerShape(40.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                        border = borderMail
                    ) {
                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            shape = RoundedCornerShape(40.dp),
                            placeholder = { Text(text = "Correo electrónico") },
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Mail,
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
                                    isEmailFocus = it.isFocused
                                }
                        )
                    }

                    if(isEmailError && email != ""){

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "* Correo inválido",
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .clickable(enabled = true) {
                                    generateToast(
                                        context,
                                        """ejemplo@ejemplo.com""",
                                        Toast.LENGTH_SHORT
                                    )
                                },
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                    } else {
                        Spacer(modifier = Modifier.padding(20.dp))
                    }

                    currentUser = email

                    var isNameFocus by remember {
                        mutableStateOf(false)
                    }
                    var name by remember { mutableStateOf("") }
                    var isNameError by remember { mutableStateOf(false) }
                    val regex = Regex("""[^!@#\$%^&*()_+\-=\[\]{};':",./<>?\\|]+""")
                    isNameError = !name.matches(regex)

                    var borderName: BorderStroke =

                        if(!isNameFocus && name == "") {
                            BorderStroke(0.dp, Color.Transparent)
                        } else if (isNameFocus && name == ""){
                            BorderStroke(2.dp, Color.Blue)
                        } else if(isNameError){
                            BorderStroke(2.dp, Color.Red)
                        } else {
                            BorderStroke(2.dp, Color.Green)
                        }


                    Card(
                        shape = RoundedCornerShape(40.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                        border = borderName
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

                    if(isNameError && name != ""){

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "* Caracteres prohibidos",
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
                    var age by remember { mutableStateOf("Introduzca la edad") }
                    var validAge by remember { mutableStateOf(false) }
                    var borderAge : BorderStroke =
                        if (isAgeFocus) BorderStroke(2.dp, Color.Blue)
                        else if(age != "Introduzca la edad"){
                            BorderStroke(2.dp, Color.Green)
                        }
                        else {
                            BorderStroke(0.dp, Color.Transparent)
                        }

                    Card(
                        shape = RoundedCornerShape(40.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                        border = borderAge,
                    ) {
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

                    var password by remember { mutableStateOf("") }

                    // -1 bad format, 0 ok, 1 vacía, 2 sin seleccionar
                    var passwordState by remember { mutableIntStateOf(-2) }

                    if(isPasswordFocus){
                        passwordState = 1

                        // una minúscula, una mayúscula y un número, 6 veces, esto como mínimo
                        // https://stackoverflow.com/questions/19605150/regex-for-password-must-contain-at-least-eight-characters-at-least-one-number-a
                        if(password.matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}\$")))
                        {passwordState = 0}
                        else {
                            if(password == ""){
                                passwordState = 1
                            } else {
                                passwordState = -1
                            }
                        }
                    }

                    var borderColor: BorderStroke =
                        if (passwordState == 0) BorderStroke(2.dp, Color.Green)
                        else if(passwordState == -1) BorderStroke(2.dp, Color.Red)
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
                            modifier = Modifier
                                .width(280.dp)
                                .onFocusChanged {
                                    // Se asigna el valor de isFocused(true or false) a la variable isFocus
                                    isPasswordFocus = it.isFocused
                                }
                        )
                    }
                    if(passwordState == -1){
                        Text(
                            text = "* Requisitos mínimos obligatorios",
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(start = 15.dp, top = 10.dp)
                                .clickable(enabled = true) {
                                    generateToast(
                                        context,
                                        """Seis carácteres, uno en mayúscula, uno en minúscula y un número""",
                                        Toast.LENGTH_LONG
                                    )
                                },
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                    } else {
                        Spacer(modifier = Modifier.padding(20.dp))
                    }




                    var isRepeatPasswordFocus by remember {
                        mutableStateOf(false)
                    }
                    var repeatPassword: String by remember { mutableStateOf("") }
                    var repeatPasswordState by remember { mutableIntStateOf(-2) }
                    var borderRepeatPassword: BorderStroke =
                        if (isRepeatPasswordFocus && repeatPassword == "") BorderStroke(2.dp, Color.Blue)
                        else if(repeatPassword != "" && (password != repeatPassword) ){
                            BorderStroke(2.dp, MaterialTheme.colorScheme.error)
                        } else if((repeatPassword != "" && (password == repeatPassword))){
                            BorderStroke(2.dp, Color.Green)
                        }

                        else {
                            BorderStroke(0.dp, Color.Transparent)
                        }

                    Card(
                        shape = RoundedCornerShape(40.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                        border = borderRepeatPassword
                    )
                    {
                        var isPasswordVisible by remember { mutableStateOf(false) }
                        TextField(
                            value = repeatPassword,
                            onValueChange = { repeatPassword = it },
                            shape = RoundedCornerShape(40.dp),
                            placeholder = { Text(text = "Repita la contraseña", maxLines = 1,overflow = TextOverflow.Ellipsis) },
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
                            modifier = Modifier
                                .width(280.dp)
                                .onFocusChanged {
                                    // Se asigna el valor de isFocused(true or false) a la variable isFocus
                                    isRepeatPasswordFocus = it.isFocused
                                }
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Row(Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        var userCanRegister by remember { mutableStateOf(false) }

                        ElevatedButton(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White, // TODO --> CAMBIAR  COLORES
                                contentColor = Color.Magenta // TODO --> CAMBIAR  COLORES
                            ),

                            onClick = {
                                if( !isEmailError && !isNameError && age.matches(regex = Regex("\\d+")) && passwordState == 0 && repeatPassword == password){

                                    // var result = FirebaseUtils.createUser(email, password)
                                    viewModel.createUserWithEmailAndPassword(email, password, successActions = {
                                        userCanRegister = true
                                    })
                                    // Failed Actions
                                    {
                                        Toast.makeText(context, "Correo ya existente", Toast.LENGTH_SHORT).show();
                                        userCanRegister = false
                                    }

                                } else {
                                    // Checkear todos los campos que faltan y ponerlos en rojo
                                    generateToast(context, "Rellene todos los campos", Toast.LENGTH_SHORT)

                                }
                            }

                        ) {
                            Text(text = "Registrarme")
                        }
                        if(userCanRegister){
                            viewModel.createUser(name, age.toInt())
                            var intent = Intent(context, FirstActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                            // Solución del problema de que salgan varias pantallas(parece ser que es porque es compostable y se llama varias veces)
                            // Entonces utilizamos un DisposableEffect que se ejecuta cuando ya no sea compostable
                            // https://stackoverflow.com/questions/76760860/what-is-disposableeffect-and-under-the-hood-in-jetpack-compose
                            DisposableEffect(context){
                                context.startActivity(intent)
                                onDispose {  }
                            }
                        }
                    }
                }
            }

        }

    }

}
