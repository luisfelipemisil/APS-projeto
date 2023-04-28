package com.app.myapplication.model.repository

import com.app.myapplication.model.entitie.Animal
import com.app.myapplication.model.entitie.CPF
import com.app.myapplication.model.entitie.User

class IRepositorioFilaCastracao: RepositorioPedidos {

    override fun setup(): Boolean {
        TODO("Not yet implemented")
    }

    override fun addPedido(ficha: Animal): Boolean {
        TODO("Not yet implemented")
    }

    override fun removerPedido(nome: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun findPedido(nome: String): Pair<Boolean, Animal> {
        TODO("Not yet implemented")
    }

    override fun editPedido(ficha: Animal): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAll(): MutableList<Animal> {
        TODO("Not yet implemented")
    }


}