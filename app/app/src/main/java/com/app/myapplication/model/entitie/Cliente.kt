package com.app.myapplication.model.entitie

data class Cliente(
    var nome: String,
    var email: Email
){
    constructor(): this("Luis", Email())
}
