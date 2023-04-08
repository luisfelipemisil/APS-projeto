package com.app.myapplication.model.collection

import android.content.Context
import com.app.myapplication.model.entitie.*
import com.app.myapplication.model.repository.AbstractFactory

class Usuarios(fabrica: AbstractFactory, context: Context) {

    val repositorio = fabrica.repositorioUsuarioSQLite(context)


    companion object{
        private var users: MutableList<User> = mutableListOf()
    }

    fun setup():Boolean{
        return repositorio.setup()
    }

    fun getAllData() :MutableList<User>{
        repositorio.setup()
        users = repositorio.getAll()
        return users
    }

    fun validarCadastroDados(usuario: User):Boolean {
        if (!usuario.validarSenhas()){
            return false
        }else if( !usuario.email.isValid()){
            return false
        }else if( usuario.nome.equals("")){
            return false
        }else if( !usuario.cpf.isValid()){
            return false
        }else if(!usuario.cartao.isValid()){
            return false
        }else if (users.find { element -> element.email.endereco == usuario.email.endereco} != null){
            return false
        }
        return true
    }

    fun addUsuario(usuario: User):Boolean{
        repositorio.setup()
        if(!validarCadastroDados(usuario)){
            print("ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ")
            return false
        }
        print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        users.add(usuario)
        return repositorio.addUsuario(usuario)
    }

    fun rmUsuario(email: String):Boolean {
            repositorio.setup()
            if (repositorio.removerUsuario(email)){
                print(users)
                val usuario = users.find { element -> element.email.endereco == email }
                if (usuario != null) {
                    users.remove(usuario)
                }
                return true
            }else {
                return false
            }
    }

    fun show(){
        print("SHOW: $users")
    }
    fun findUsuario(email: String): Pair<Boolean, User>{
        getAllData()
        val res = users.find { element -> element.email.endereco == email}

        if(res != null){
            return Pair(true, res)
        }
        return Pair(false, User())
    }

    fun editUsuario(usuario: User):Boolean{
        repositorio.setup()
        val index = users.indexOf(usuario)
        if(index > 0){
            users.set(index,usuario)
        }else{
            users.add(usuario)
        }

        if(!repositorio.editUsuario(usuario)){
            return false
        }
        return true
    }

}