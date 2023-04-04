package com.app.myapplication.model.collection

import com.app.myapplication.model.entitie.CPF
import com.app.myapplication.model.entitie.Cartao
import com.app.myapplication.model.entitie.Email
import com.app.myapplication.model.entitie.User

class Usuarios {
    companion object{
        var users: MutableList<User> = mutableListOf()

        fun validarCadastroDados(usuario: User):Boolean {
            if (!usuario.validarSenhas()){
                return false
            }else if( !usuario.email.isValid()){
                return false
            }else if( usuario.nome.equals("")){
                return false
            }else if( !usuario.cpf.isValid()){
                return false
            }else if(!usuario.cartao.isValid()){
                return false
            }
            return true
        }

        fun addUsuario(usuario: User):Boolean{
            if(!validarCadastroDados(usuario)){
                return false
            }
            users.add(usuario)
            return true
        }
    }
}