package com.app.myapplication.model.entitie

data class Cartao (
    var numero :String,
    var validade: String,
    var codigo: String ){

    fun isValid(): Boolean {
        return true
    /*
        var s1 = 0
        var s2 = 0
        val reverse = StringBuffer(numero).reverse().toString()
        for (i in reverse.indices) {
            val digit = Character.digit(reverse[i], 10)
            when {
                i % 2 == 0 -> s1 += digit
                else -> {
                    s2 += 2 * digit
                    when {
                        digit >= 5 -> s2 -= 9
                    }
                }
            }
        }
        return (s1 + s2) % 10 == 0
        */
    }

    companion object {
        private const val DECIMAL_BASE = 10
    }
}