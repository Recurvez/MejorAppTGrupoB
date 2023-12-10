package com.example.mejorapptgrupob.internalDB;

/*class LvlCalc {
    fun calcularNiveles(edad: Int, sexo:String, factor: String): String {
        val niveles: Map<String, Triple<Float, Float, Float>> = mutableMapOf(
                "mujeres" to Triple(19.2f, 30f, 34.1f),
                "hombres" to Triple(13.2f, 20.4f, 28f),
                "no binario" to Triple(16.2f, 25.2f, 27f)
        )

        val percentil50 = niveles[sexo]!!.first
        val percentil70 = niveles[sexo]!!.second
        val percentil80 = niveles[sexo]!!.third

        val valor: Float = when (factor) {
            "Factor01" -> edad * 0.1f + 10f
            "Factor02" -> edad * 0.05f
            "Factor03" -> edad * 0.2f
            else -> throw IllegalArgumentException("Factor no reconocido")
        }

        if (valor <= percentil50) {
            return "bajo"
        } else if (valor <= percentil70) {
            return "medio"
        } else {
            return "alto"
        }
    }
} */

