package com.app.myapplication.model.collection

import com.app.myapplication.model.entitie.Resgate

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