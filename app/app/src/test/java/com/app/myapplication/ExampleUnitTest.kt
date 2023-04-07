package com.app.myapplication

import com.app.myapplication.controller.ControllerUsuario
import com.app.myapplication.model.collection.Usuarios
import com.app.myapplication.model.entitie.User
import com.app.myapplication.model.repository.fabricaRepositorioFilaCastracao
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testeRepositoryConnection(){
        var abstractFactory = fabricaRepositorioFilaCastracao()
        val repositorio = abstractFactory.repositorioUsuario()

        val res = repositorio.setup()
        assert(res)
    }

    @Test
    fun testGetAllUsers(){
        var abstractFactory = fabricaRepositorioFilaCastracao()
        val repositorio = abstractFactory.repositorioUsuario()
        repositorio.setup()
        val res = repositorio.getAll()
        val tes: MutableList<User> = mutableListOf()

        assert(res != tes )
    }
}