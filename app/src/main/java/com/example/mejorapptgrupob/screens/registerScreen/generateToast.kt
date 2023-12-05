package com.example.mejorapptgrupob.screens.registerScreen

import android.content.Context
import android.widget.Toast

internal fun generateToast(context: Context, content: String, duration: Int){
    Toast.makeText(context, content, duration).show()
}