package com.app.myapplication.model.collection

import com.app.myapplication.model.entitie.*

class CollectionDoacoes private constructor(){
    companion object{
        var doacoes: MutableList<Doacao> = mutableListOf()

        fun validarDoacao(doacao: Doacao):Boolean {
            return doacoes.contains(doacao)
        }

    }
}

class CollectionUsuarios private constructor(){
    companion object{
        var users: MutableList<User> = mutableListOf()

        fun validarUser(usuario:User):Boolean {
            return users.contains(usuario)
        }

    }
}

class CollectionEquipeCuidado private constructor(){
    companion object{
        var equipeCuidados: MutableList<Cuidado> = mutableListOf()

        fun enviarEmail(){
            for (equipe in equipeCuidados) {
                //enviar email para cada
                print(equipe.email.endereco)
            }
        }
    }
}

class CollectionEquipeResgate private constructor(){
    companion object{
        var equipeResgate: MutableList<Resgate> = mutableListOf()

        fun enviarEmail(){
            for (equipe in equipeResgate) {
                //enviar email para cada
                print(equipe.email.endereco)
            }
        }
    }
}

class FilaCastracao(){
    companion object {
        var fila: MutableList<Animal> = mutableListOf()

        fun mudarStatus(status:String, animal: Animal){
            animal.mudarStatus(status)
        }
    }
}

class FilaAdocao(){
    companion object {
        var fila: MutableList<Animal> = mutableListOf()

        fun mudarStatus(status:String, animal: Animal){
            animal.mudarStatus(status)
        }
    }
}

class Veterinarios(){
    companion object {
        var fila: MutableList<Veterinario> = mutableListOf()

        fun notificarVeterinarios(status:String){
            for (veterinario in fila){
                //enviar email
                print("${veterinario.nome} ${veterinario.email}")
            }
        }
    }
}

class EquipeAdocao(){
    companion object {
        var fila: MutableList<EquipeAnaliseDoacao> = mutableListOf()

        fun validacaoAdocao(status:String){
            for (equipe in fila){
                //enviar email
                print("${equipe.nome} ${equipe.email}")
            }
        }
    }
}

