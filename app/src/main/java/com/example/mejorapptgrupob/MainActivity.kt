package com.example.mejorapptgrupob
import com.example.mejorapptgrupob.screens.mainScreen.MainScreen
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.mejorapptgrupob.screens.loginScreen.LoginScreen
import com.example.mejorapptgrupob.screens.registerScreen.RegisterScreen

class MainActivity : ComponentActivity() {

    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen.LoginLayout(dataStore = dataStore)
                
                }
            }
        }
    }
}
