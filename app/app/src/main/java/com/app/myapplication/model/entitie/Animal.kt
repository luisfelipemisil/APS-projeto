package com.app.myapplication.model.entitie

data class Animal(
    var nome:String,
    var status: String,
    var idade: Int,
    var raça: String,
    var filhote: Boolean,
    var endereço: String,
    var cliete: Clientes
){
    fun mudarStatus(status: String){
        this.status = status
    }

    fun notificarCliente(status: String) {
        // enviar email
        print(cliete.email.endereco)
    }
}