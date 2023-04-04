package com.app.myapplication.model.collection

import com.app.myapplication.model.entitie.Doacao

class CollectionDoacoes private constructor(){
    companion object{
        var doacoes: MutableList<Doacao> = mutableListOf()

        fun validarDoacao(doacao: Doacao):Boolean {
            return doacoes.contains(doacao)
        }

    }
}