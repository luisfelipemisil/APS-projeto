package com.app.myapplication.model.collection

import com.app.myapplication.model.entitie.Cuidado

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
