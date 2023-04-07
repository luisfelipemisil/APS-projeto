package com.app.myapplication.controller

import com.app.myapplication.model.collection.Usuarios
import com.app.myapplication.model.entitie.Animal
import com.app.myapplication.model.entitie.User

class Fachada {

    companion object {
        var ctrlUser: ControllerUsuario
        var ctrlVeterinarios: ControllerVeterinario
        var ctrlFilaCastracao: ControllerFilaCastracao
        var ctrlControAdocao: ControllerAdocao
        var ctrlFuncionario: ControllerFuncionario

        init {
            ctrlUser = ControllerUsuario()
            ctrlVeterinarios = ControllerVeterinario()
            ctrlFilaCastracao = ControllerFilaCastracao()
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
    }


}