package com.example.mejorapptgrupob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mejorapptgrupob.screens.firstScreen.FirstScreen
import com.example.mejorapptgrupob.screens.loginScreen.LoginScreen
import com.example.mejorapptgrupob.screens.mainScreen.MainScreen
import com.example.mejorapptgrupob.screens.registerScreen.RegisterScreen
import com.example.mejorapptgrupob.screens.userGuideScreen.UserGuideScreen

class MainActivity : ComponentActivity() {
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
                    //FirstScreen.FirstLayout()
                    //MainScreen.MainLayout()
                   //UserGuideScreen.UserGuideLayout()

                    // MainScreen.MainLayout()

                    // LoginScreen.LoginLayout()
                    RegisterScreen.RegisterLayout()

                }
            }
        }
    }
}

/* Os lo dejo de ejemplo por si quereís utilizar la preview en otras clases, esto va fuera

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        Greeting("Android")
    }
}
*/
