package com.example.mejorapptgrupob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mejorapptgrupob.screens.testScreen.TestScreen
import com.example.mejorapptgrupob.screens.testScreen.TestScreen10
import com.example.mejorapptgrupob.screens.testScreen.TestScreen11
import com.example.mejorapptgrupob.screens.testScreen.TestScreen12
import com.example.mejorapptgrupob.screens.testScreen.TestScreen13
import com.example.mejorapptgrupob.screens.testScreen.TestScreen14
import com.example.mejorapptgrupob.screens.testScreen.TestScreen15
import com.example.mejorapptgrupob.screens.testScreen.TestScreen2
import com.example.mejorapptgrupob.screens.testScreen.TestScreen3
import com.example.mejorapptgrupob.screens.testScreen.TestScreen4
import com.example.mejorapptgrupob.screens.testScreen.TestScreen5
import com.example.mejorapptgrupob.screens.testScreen.TestScreen6
import com.example.mejorapptgrupob.screens.testScreen.TestScreen7
import com.example.mejorapptgrupob.screens.testScreen.TestScreen8
import com.example.mejorapptgrupob.screens.testScreen.TestScreen9

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
                    TestScreen.Screen()

                    // MainScreen.MainLayout()

                    // LoginScreen.LoginLayout()
                    // RegisterScreen.RegisterLayout()

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
