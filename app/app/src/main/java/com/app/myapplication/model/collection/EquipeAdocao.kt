package com.app.myapplication.model.collection

import com.app.myapplication.model.entitie.EquipeAnaliseAdoacao

class EquipeAdocao(){
    companion object {
        var fila: MutableList<EquipeAnaliseAdoacao> = mutableListOf()

        fun validacaoAdocao(status:String){
            for (equipe in fila){
                //enviar email
                print("${equipe.nome} ${equipe.email}")
            }
        }
    }
}
