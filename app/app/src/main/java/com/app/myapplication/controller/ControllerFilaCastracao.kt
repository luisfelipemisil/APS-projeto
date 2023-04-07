package com.app.myapplication.controller

import com.app.myapplication.model.collection.FilaCastracao
import com.app.myapplication.model.entitie.Animal
import com.app.myapplication.model.repository.AbstractFactory
import com.app.myapplication.model.repository.fabricaRepositorioFilaCastracao

class ControllerFilaCastracao {
    var abstractFactory = fabricaRepositorioFilaCastracao()
    var filaCastracao = FilaCastracao(abstractFactory)

    fun registrarPedido(animal:Animal): Boolean{
        return filaCastracao.addFila(animal)
    }
}