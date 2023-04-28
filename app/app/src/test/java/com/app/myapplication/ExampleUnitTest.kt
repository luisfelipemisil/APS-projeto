package com.app.myapplication

import android.content.Context
import com.app.myapplication.model.collection.Usuarios
import com.app.myapplication.model.entitie.CPF
import com.app.myapplication.model.entitie.Cartao
import com.app.myapplication.model.entitie.Email
import com.app.myapplication.model.entitie.User
import com.app.myapplication.model.repository.fabricaRepositorioSqlit
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class ExampleUnitTest {
    @Test
    fun testeRepositoryConnection(){
        var abstractFactory = fabricaRepositorioSqlit()
        val mockContext: Context = Mockito.mock(Context::class.java)
        val repositorio = abstractFactory.repositorioUsuarioSQLite(mockContext)
        val res = repositorio.setup()
        assert(res)
    }

    @Test
    fun testAdduserRepositorio(){
        val abstractFactory = fabricaRepositorioSqlit()
        val mockContext: Context = Mockito.mock(Context::class.java)
        val repositorio = abstractFactory.repositorioUsuarioSQLite(mockContext)
        repositorio.setup()
        val user = User("Luis", "123456", "123456", Email("luis@gmail.com"), Cartao("1", "1", "1"), CPF("1"))
        val res = repositorio.addUsuario(user)
        assert(res)
    }

    @Test
    fun testEditUserRepositorio(){
        val abstractFactory = fabricaRepositorioSqlit()
        val repositorio = abstractFactory.repositorioUsuario()
        repositorio.setup()
        val user = User("Luis", "654321", "654321", Email("luis@gmail.com"), Cartao("1", "1", "1"), CPF("1"))
        val res = repositorio.editUsuario(user)
        assert(res)
    }

    @Test
    fun testFindUserRepositorio(){
        val abstractFactory = fabricaRepositorioSqlit()
        val repositorio = abstractFactory.repositorioUsuario()
        repositorio.setup()
        val res = repositorio.findUsuario("luis@gmail.com")
        assert(res.first)
    }

    @Test
    fun testGetAllUsersRepositorio(){
        val abstractFactory = fabricaRepositorioSqlit()
        val repositorio = abstractFactory.repositorioUsuario()
        repositorio.setup()
        val res = repositorio.getAll()
        val tes: MutableList<User> = mutableListOf()
        assert(res != tes )
    }

    @Test
    fun testFindDeleteRepositorio(){
        val abstractFactory = fabricaRepositorioSqlit()
        val repositorio = abstractFactory.repositorioUsuario()
        repositorio.setup()
        val user = User("Luis", "654321", "654321", Email("luis@gmail.com"), Cartao("1", "1", "1"), CPF("1"))
        val res = repositorio.removerUsuario(user.email.endereco)
        assert(res)
    }

    @Test
    fun testAddUser(){
        val abstractFactory = fabricaRepositorioSqlit()
        val mockContext: Context = Mockito.mock(Context::class.java)
        val usuarios = Usuarios(abstractFactory,mockContext)
        val user = User("Luis", "654321", "654321", Email("luis@gmail.com"), Cartao("1", "1", "1"), CPF("1"))
        val res = usuarios.addUsuario(user)
        assert(res)
    }

    @Test
    fun testFindUser(){
        val abstractFactory = fabricaRepositorioSqlit()
        val mockContext: Context = Mockito.mock(Context::class.java)
        val usuarios = Usuarios(abstractFactory,mockContext )
        val res = usuarios.findUsuario("luis@gmail.com")
        println("NOME: ${res.second.nome}")
        assert(res.first)
    }

    @Test
    fun testEditUser(){
        val abstractFactory = fabricaRepositorioSqlit()
        val mockContext: Context = Mockito.mock(Context::class.java)
        val usuarios = Usuarios(abstractFactory, mockContext)
        val user = User("Luis", "123456", "123456", Email("luis@gmail.com"), Cartao("1", "1", "1"), CPF("1"))
        val res = usuarios.editUsuario(user)
        usuarios.show()
        assert(res)
    }

    @Test
    fun testRmUser(){
        val abstractFactory = fabricaRepositorioSqlit()
        val mockContext: Context = Mockito.mock(Context::class.java)
        val usuarios = Usuarios(abstractFactory, mockContext)
        val res = usuarios.rmUsuario("luis@gmail.com")
        usuarios.show()
        assert(res)
    }
}