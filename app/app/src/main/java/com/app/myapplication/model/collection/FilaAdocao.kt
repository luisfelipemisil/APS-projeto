package com.app.myapplication.model.collection

import com.app.myapplication.model.entitie.Animal

class FilaAdocao(){
    companion object {
        var fila: MutableList<Animal> = mutableListOf()

        fun mudarStatus(status:String, animal: Animal){
            animal.mudarStatus(status)
        }
    }
}