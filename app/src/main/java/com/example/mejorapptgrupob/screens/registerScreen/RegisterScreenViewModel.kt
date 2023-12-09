package com.example.mejorapptgrupob.screens.registerScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterScreenViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _isLoading = MutableLiveData(false)

    var inProgress = mutableStateOf(false)

    internal fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        actions: () -> Unit
    ){
        if(_isLoading.value == false){
            _isLoading.value = true

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        actions()
                    } else {
                        Log.d("Registro", "createUserWithEmailAndPassword ${task.result}")
                    }
                    _isLoading.value = false
                }
        }
    }

}