package com.example.mejorapptgrupob.screens.testScreen.model

data class Results(
    val id: String,
    val nick: String,
    val fecha: String,
    val sumFact01: Int,
    val sumFact02: Int,
    val sumFact03: Int
) {
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "id" to this.id,
            "nick" to nick,
            "fecha" to fecha,
            "sumFact01" to sumFact01,
            "sumFact02" to sumFact02,
            "sumFact03" to sumFact03
        )
    }
}
