package com.app.myapplication.model.repository

import com.app.myapplication.model.collection.Usuarios
import com.app.myapplication.model.entitie.Animal
import com.app.myapplication.model.entitie.User

interface RepositorioUsuario {

    fun addUsuario(user: User):Boolean

    fun removerUsuario(user: User):Boolean

    fun findUsuario(email: String): Pair<Boolean, User>

    fun editUsuario(user: User):Boolean

    fun getAll():MutableList<User>
}