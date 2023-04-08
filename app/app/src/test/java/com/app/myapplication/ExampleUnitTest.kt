package com.app.myapplication

import com.app.myapplication.controller.ControllerUsuario
import com.app.myapplication.model.collection.Usuarios
import com.app.myapplication.model.entitie.CPF
import com.app.myapplication.model.entitie.Cartao
import com.app.myapplication.model.entitie.Email
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
    fun testAdduserRepositorio(){
        val abstractFactory = fabricaRepositorioFilaCastracao()
        val repositorio = abstractFactory.repositorioUsuario()
        repositorio.setup()
        val user = User("Luis", "123456", "123456", Email("luis@gmail.com"), Cartao("1", "1", "1"), CPF("1"))
        val res = repositorio.addUsuario(user)
        assert(res)
    }

    @Test
    fun testEditUserRepositorio(){
        val abstractFactory = fabricaRepositorioFilaCastracao()
        val repositorio = abstractFactory.repositorioUsuario()
        repositorio.setup()
        val user = User("Luis", "654321", "654321", Email("luis@gmail.com"), Cartao("1", "1", "1"), CPF("1"))
        val res = repositorio.editUsuario(user)
        assert(res)
    }

    @Test
    fun testFindUserRepositorio(){
        val abstractFactory = fabricaRepositorioFilaCastracao()
        val repositorio = abstractFactory.repositorioUsuario()
        repositorio.setup()
        val res = repositorio.findUsuario("luis@gmail.com")
        assert(res.first)
    }

    @Test
    fun testGetAllUsersRepositorio(){
        val abstractFactory = fabricaRepositorioFilaCastracao()
        val repositorio = abstractFactory.repositorioUsuario()
        repositorio.setup()
        val res = repositorio.getAll()
        val tes: MutableList<User> = mutableListOf()
        assert(res != tes )
    }

    @Test
    fun testFindDeleteRepositorio(){
        val abstractFactory = fabricaRepositorioFilaCastracao()
        val repositorio = abstractFactory.repositorioUsuario()
        repositorio.setup()
        val user = User("Luis", "654321", "654321", Email("luis@gmail.com"), Cartao("1", "1", "1"), CPF("1"))
        val res = repositorio.removerUsuario(user)
        assert(res)
    }
}