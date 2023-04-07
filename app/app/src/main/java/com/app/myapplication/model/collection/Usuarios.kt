package com.app.myapplication.model.collection

import com.app.myapplication.model.entitie.*
import com.app.myapplication.model.repository.AbstractFactory

class Usuarios(fabrica: AbstractFactory) {

    val repositorio = fabrica.repositorioUsuario()

    init {
        getAllData()
    }
    companion object{
        var users: MutableList<User> = mutableListOf()
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

    fun rmUsuario(cpf: String):Boolean {
        val (ok, usuario) = findUsuario(cpf)
        if (ok){
            if (repositorio.removerUsuario(usuario)){
                users.remove(usuario)
            }else {
                return false
            }
        }else{
            return false
        }
        return true
    }

    fun findUsuario(email: String): Pair<Boolean,User>{
        val (ok, usuario) = repositorio.findUsuario(email)
        if (ok){
            return Pair(true, usuario)
        }
        return Pair(false, usuario)
    }

    fun editUsuario(usuario: User):Boolean{
        getAllData()
        val index = users.indexOf(usuario)
        if(index > 0){
            users.set(index,usuario)
        }else{
            return false
        }
        if(!repositorio.editUsuario(usuario)){
            return false
        }
        return true
    }

}