package com.app.myapplication.controller

import android.content.Context
import com.app.myapplication.model.collection.FilaCastracao
import com.app.myapplication.model.collection.Usuarios
import com.app.myapplication.model.entitie.Animal
import com.app.myapplication.model.entitie.User
import com.app.myapplication.model.repository.fabricaRepositorioFilaCastracao
import kotlin.math.log

class ControllerUsuario(context: Context) {

    var abstractFactory = fabricaRepositorioFilaCastracao()
    var usuarios = Usuarios(abstractFactory,context )

    companion object {
        var logged = false
        var user = User()
    }

    fun hasUser():Pair<Boolean, User>{
        return Pair(logged, user)
    }

    fun registrarUsuario(usuario: User): Boolean{
        return usuarios.addUsuario(usuario)
    }

    fun trocarSenha(usuario: User):Boolean{
        return usuarios.editUsuario(usuario)
    }

    fun deletarUsuario(usuario: User):Boolean{
        return usuarios.rmUsuario(usuario.cpf.registro)
    }

    fun validarUsuario(usuario: User):Pair<Boolean,User>{
        val (ok, finded_usuario) = usuarios.findUsuario(usuario.email.endereco)
        if(ok){
            logged = !logged
            user = usuario
        }
        return Pair(ok, finded_usuario)
    }

}