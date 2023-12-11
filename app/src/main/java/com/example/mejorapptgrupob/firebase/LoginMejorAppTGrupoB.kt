package com.example.mejorapptgrupob.firebase

import android.app.Application
import com.google.firebase.FirebaseApp

class LoginMejorAppTGrupoB : Application() {
    override fun onCreate(){
        super.onCreate()
        // Initialize the services for use firebase
        FirebaseApp.initializeApp(this)
    }
}