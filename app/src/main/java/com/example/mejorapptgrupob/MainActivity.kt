package com.example.mejorapptgrupob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import com.example.mejorapptgrupob.screens.registerScreen.RegisterScreen
=======
import com.example.mejorapptgrupob.screens.mainScreen.MainScreen
>>>>>>> 4ba810d060fdfeffb8e689b66b6861192b4181cd

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
                    MainScreen.MainLayout()
                    //UserGuideScreen.UserGuideLayout()
                    // MainScreen.MainLayout()
                    // InfoScreen.InfoLayout()
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
