package com.example.mejorapptgrupob.firebase

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class FirebaseUtils {



    companion object{
        var canRegister by mutableStateOf(false)
        var emailExist by mutableStateOf(false)

        /* Pueden devolver un Flow<Resource<AuthResult>> que el estado, si se ha subido bien, si ha dado error... */

        /*0 -> registro correcto, 1 --> correo existente*/

        internal fun createUser(email: String, password: String) {

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)


                // Cuando se completa la operación, ya sea exitosamente o con un fallo
                .addOnCompleteListener {
                    Log.d(TAG, "Inside_addOnCompleteListener")
                    Log.d(TAG, "isSuccessful = ${it.isSuccessful}")

                    if(it.isSuccessful){
                        canRegister = true
                    }

                }

                // Si la operación falla
                .addOnFailureListener {
                    Log.d(TAG, "Inside_addOnFailureListener")
                    Log.d(TAG, "Exception = ${it.message}")
                    Log.d(TAG, "Exception = ${it.localizedMessage}")
                }
        }

        internal fun logout(){
            val firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()

            // Check if the current user logout
            val authStateListener = AuthStateListener{
                if(it.currentUser == null){
                    Log.d(TAG, "User logout")
                }
            }
        }

        internal fun login(email: String, password: String){
            FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(email, password)

                .addOnCompleteListener{
                    if(it.isSuccessful){

                    }
                }
                .addOnFailureListener{ excepction ->
                    when (excepction) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            Log.d("Login", "Credenciales inválidas")
                        }
                        else -> {
                            Log.d("Login", "Excepción desconocida: ${excepction.message}")
                        }
                    }
                }
        }
    }
}