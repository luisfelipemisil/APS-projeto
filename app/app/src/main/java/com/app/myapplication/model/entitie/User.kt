package com.app.myapplication.model.entitie

data class User(
    var nome: String,
    var senha: String,
    var segundaSenha: String,
    var email: Email,
    var cartao: Cartao,
    var cpf: CPF
){
    fun validarSenhas():Boolean{
        return (senha == segundaSenha)
    }
}
