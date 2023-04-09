package com.app.myapplication.controller

import android.content.Context
import com.app.myapplication.model.collection.Usuarios
import com.app.myapplication.model.entitie.Animal
import com.app.myapplication.model.entitie.User

class Fachada(context: Context) {



        var ctrlUser: ControllerUsuario
        var ctrlVeterinarios: ControllerVeterinario
        var ctrlFilaCastracao: ControllerFilaCastracao
        var ctrlControAdocao: ControllerAdocao
        var ctrlFuncionario: ControllerFuncionario

        init {
            ctrlUser = ControllerUsuario(context)
            ctrlVeterinarios = ControllerVeterinario()
            ctrlFilaCastracao = ControllerFilaCastracao(context)
            ctrlControAdocao = ControllerAdocao()
            ctrlFuncionario = ControllerFuncionario()
        }

        fun registrarPedidoCastracao(animal: Animal): Boolean {
            return ctrlFilaCastracao.registrarPedido(animal)
        }

        fun addUsuario(usuarios: User): Boolean {
            return ctrlUser.registrarUsuario(usuarios)
        }

        fun validarUsuario(usuarios: User): Pair<Boolean, User> {
            return ctrlUser.validarUsuario(usuarios)
        }

        fun editarSenhaUsuario(usuarios: User): Boolean {
            return ctrlUser.trocarSenha(usuarios)
        }

    fun logout(usuarios: User){
        ctrlUser.login(usuarios)
    }


    fun logout(){
        ctrlUser.logout()
    }


    fun hasUser():Pair<Boolean, User>{
        return ctrlUser.hasUser()
    }


}