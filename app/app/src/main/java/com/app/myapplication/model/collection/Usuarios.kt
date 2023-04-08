package com.app.myapplication.model.collection

import com.app.myapplication.model.entitie.*
import com.app.myapplication.model.repository.AbstractFactory

class Usuarios(fabrica: AbstractFactory) {

    val repositorio = fabrica.repositorioUsuario()

    companion object{
        private var users: MutableList<User> = mutableListOf()
    }

    fun getAllData() :MutableList<User>{
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
        if(!validarCadastroDados(usuario)){
            return false
        }
        users.add(usuario)
        return repositorio.addUsuario(usuario)
    }

    fun rmUsuario(email: String):Boolean {
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