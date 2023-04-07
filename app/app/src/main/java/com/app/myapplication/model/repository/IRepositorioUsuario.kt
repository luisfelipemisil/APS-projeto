package com.app.myapplication.model.repository

import com.app.myapplication.model.collection.Usuarios
import com.app.myapplication.model.entitie.User

class IRepositorioUsuario : RepositorioUsuario {
    override fun addUsuario(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun removerUsuario(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun findUsuario(email: String): Pair<Boolean, User> {
        TODO("Not yet implemented")
    }

    override fun editUsuario(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAll(): MutableList<User> {
        TODO("Not yet implemented")
    }
}