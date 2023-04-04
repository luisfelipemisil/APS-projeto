package com.app.myapplication.model.collection

import com.app.myapplication.model.entitie.Veterinario

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