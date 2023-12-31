package com.example.mejorapptgrupob

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.mejorapptgrupob.internalDB.DBUtilities

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.example.mejorapptgrupob.screens.firstScreen.FirstActivity
import com.example.mejorapptgrupob.screens.firstScreen.FirstLayout

import com.example.mejorapptgrupob.screens.loginScreen.LoginActivity
import com.example.mejorapptgrupob.screens.loginScreen.LoginLayout


import com.example.mejorapptgrupob.screens.registerScreen.RegisterActivity
import com.example.mejorapptgrupob.screens.testScreen.FinalScreen
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

object DataStoreManager {
    private var _dataStore: DataStore<Preferences>? = null

    val dataStore: DataStore<Preferences>
        get() = requireNotNull(_dataStore)

    fun initializeDataStore(dataStore: DataStore<Preferences>) {
        if (_dataStore == null) {
            _dataStore = dataStore
        }
    }

}


class MainActivity : ComponentActivity() {

    val dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bloqueo de orientación
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val dbUtilities = DBUtilities(resources.openRawResource(R.raw.preguntasv1),this)
        DataStoreManager.initializeDataStore(dataStore)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Lo de si esta logueado que entre directamente, lo de las prefes de adri aún no va
                    if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
                        MainLayout()
                    } else {
                        FirstLayout()
                    }
                }
            }
        }
    }
/*

    private fun Preferences.isDataStoreEmpty(): Boolean {
        return this.asMap().isEmpty()
    }

    fun clearPreferences() {
        lifecycleScope.launch {
            dataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }
    fun checkAndRedirect() {
        lifecycleScope.launch {
            try {
                val preferences = dataStore.data.first()
                val intent = if (preferences.isDataStoreEmpty()) {
                    Intent(this@MainActivity, LoginActivity::class.java)
                } else {
                    Intent(this@MainActivity, FirstActivity::class.java)
                }
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }
    }
*/
}

@Composable
internal fun MainLayout() {

    val mContext = LocalContext.current
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
            modifier = Modifier
                .fillMaxSize()

        ) {
            Button(
                onClick = {
                    val mainActivity = (mContext as MainActivity)
                    mainActivity.lifecycleScope.launch {
                        // mainActivity.checkAndRedirect()
                    }
                    val intent = Intent(mContext, LoginActivity::class.java)

                    mContext.startActivity(intent)

                },
                modifier = Modifier.width(140.dp)
            ) {
                Text(text = "Iniciar sesión")
            }


            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedButton(
                onClick = {
                    mContext.startActivity(Intent(mContext, RegisterActivity::class.java))
                },
                modifier = Modifier.width(140.dp)
            ) {
                Text(text = "Registrarse")
            }
        }
    }
}