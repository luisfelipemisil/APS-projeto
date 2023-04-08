package com.app.myapplication.model.entitie

data class Animal(
    var nome:String,
    var status: String,
    var idade: Int,
    var raca: String,
    var gato : Boolean,
    var cachorro: Boolean,
    var filhote: Boolean,
    var endereco: String,
    var descricao: String,
    var cliente: Cliente
){
    constructor() : this("", "", -1, "", true, false, false, "", "", Cliente())
    fun mudarStatus(status: String){
        this.status = status
    }

    fun notificarCliente(status: String) {
        // enviar email
        print(cliente.email.endereco)
    }
}