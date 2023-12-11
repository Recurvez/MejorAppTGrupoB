package com.example.mejorapptgrupob.screens.registerScreen.model

data class User(
    val userId: String,
    val username: String,
    val age: Int
){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "user_id" to this.userId,
            "username" to username,
            "age" to age
        )
    }
}
