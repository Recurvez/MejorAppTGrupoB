package com.example.mejorapptgrupob.screens.registerScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mejorapptgrupob.screens.registerScreen.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
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

    internal fun createUser(username: String, age: Int){
        val userId = auth.currentUser?.uid
        // val user = mutableMapOf<String, Any>()

        val user = User(
            userId = userId.toString(),
            username = username,
            age = age
        ).toMap()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("registro", "datos usuarios añadididos, ${it.id}")
            }
            .addOnFailureListener{
                Log.d("registro", "Ocurrió un error")
            }

    }

}