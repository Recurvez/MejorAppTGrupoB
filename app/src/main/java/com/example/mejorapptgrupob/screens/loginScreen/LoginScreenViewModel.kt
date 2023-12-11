package com.example.mejorapptgrupob.screens.loginScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

// ViewModel para la retención de los datos en las diferentes etapas de la app
// https://www.youtube.com/watch?v=NFot9_bSFhw&t=433s

class LoginScreenViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    var currentUser = mutableStateOf<FirebaseUser?>(null)

    // El guión bajo se pone por convención para indicar que es una variable privada
    // MutableLiveData para que trabaje bien con los ciclos de vida de la app, garantiza que las actualizaciones de datos solo se envien cuando el componente está en un estado activo
    // Se suele utilziar mucho para representar estados de una aplicación, también para almacenar datos que pueden ser observados y actualizados
    private val _isLoading = MutableLiveData(false)

    var inProgress = mutableStateOf(false)

    internal fun signInWithEmailAndPassword(email: String, password: String, sucessActions: () -> Unit , failedActions: () -> Unit ) =
        // Escribiremos el contenido de la función en un viewModelScope para que se ejecute en segundo plano
        viewModelScope.launch {

            // Indicamos que está en proceso de creación de usuario
            inProgress.value = true

            // Importante recordar que esto es asincrono, no espera a nadie
            auth.signInWithEmailAndPassword(email, password)

            // Cuando se haya realizado la tarea, de fallo o no
            .addOnCompleteListener{ task ->

                if(task.isSuccessful){
                    // Si su estado es correcto
                    Log.d("Login", "Logueado con éxito:)")
                    sucessActions()
                } else {
                    failedActions()
                    task.exception
                    when(val exception = task.exception) {
                        is FirebaseException -> {
                            Log.d("Login", "Credenciales inválidas")
                        }
                        else -> {
                            Log.d("Login", "Excepción no controlada")
                            Log.d("Login", "nombre de la excepción -> ${exception?.javaClass?.simpleName}")
                        }
                    }
                }

                // Acaba el proceso de creación de usuario
                inProgress.value = false

            }

        }
    internal fun fetchCurrentUser() {
        val user = auth.currentUser
        currentUser.value = user
    }
}