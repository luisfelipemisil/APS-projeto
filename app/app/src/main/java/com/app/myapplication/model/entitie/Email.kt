package com.app.myapplication.model.entitie

data class Email(var endereco: String) {
    constructor(): this("luis@email.com")
    private val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    fun isValid(): Boolean {
        //return this.endereco.matches(regex.toRegex())
        return true
    }
}