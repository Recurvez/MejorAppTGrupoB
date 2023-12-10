package com.example.mejorapptgrupob.screens.firstScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogoutViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    internal fun logout(){
        auth.signOut()

        val authStateListener = AuthStateListener {
            if(it.currentUser == null){
                Log.d("logout", "Logout correcto")
            } else {
                Log.d("logout", "Fallo en el logout")
            }
        }

        auth.addAuthStateListener { authStateListener }

    }
}