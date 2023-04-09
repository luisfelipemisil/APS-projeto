package com.app.myapplication.model.repository

import com.app.myapplication.model.entitie.Animal
import com.app.myapplication.model.entitie.User

interface RepositorioPedidos {

    fun setup():Boolean
    fun addPedido(ficha: Animal):Boolean

    fun removerPedido(nome: String):Boolean

    fun findPedido(nome: String): Pair<Boolean, Animal>

    fun editPedido(ficha: Animal):Boolean

    fun getAll():MutableList<Animal>
}