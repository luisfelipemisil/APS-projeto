package com.app.myapplication.model.collection

import com.app.myapplication.model.entitie.Animal
import com.app.myapplication.model.entitie.User

class FilaCastracao(){
    companion object {
        var fila: MutableList<Animal> = mutableListOf()

        fun mudarStatus(status:String, animal: Animal){
            animal.mudarStatus(status)
        }

        fun validarPedidoDados(animal: Animal):Boolean {
            if( !animal.cliete.email.isValid()){
                return false
            }else if( animal.cliete.nome.equals("")){
                return false
            }

            return true
        }

        fun addAnimal(animal: Animal):Boolean{
            if(!validarPedidoDados(animal)){
                return false
            }
            fila.add(animal)
            return true
        }
    }
}