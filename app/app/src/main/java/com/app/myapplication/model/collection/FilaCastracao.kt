package com.app.myapplication.model.collection

import android.content.Context
import com.app.myapplication.model.entitie.Animal
import com.app.myapplication.model.entitie.Cliente
import com.app.myapplication.model.entitie.Email
import com.app.myapplication.model.repository.AbstractFactory

class FilaCastracao(fabrica: AbstractFactory,context: Context){

    var  repositorio = fabrica.repositorioPedidosSQLite(context )

    init {
        getAllData()
    }
    companion object {
        var fila: MutableList<Animal> = mutableListOf()
    }

    fun updateRepositorio(){
        for (animal in fila){
            repositorio.addPedido(animal)
        }
    }

    fun addFila(animal: Animal):Boolean{
        if(!validarPedidoDados(animal)){
            return false
        }
        fila.add(animal)
        updateRepositorio()
        return true
    }

    fun removerFila(animal: Animal): Boolean{
        getAllData()
        if(!fila.remove(animal)){
            return false
        }
        if(!repositorio.removerPedido(animal.nome)){
            return false
        }
        return true
    }

    fun editFila(animal: Animal):Boolean{
        getAllData()
        val index = fila.indexOf(animal)
        if(index > 0){
            fila.set(index,animal)
        }else{
            return false
        }
        if(!repositorio.editPedido(animal)){
            return false
        }
        return true
    }

    fun getAnimalByName(nomeAnimal:String):Animal{
        getAllData()
        val resposta =  fila.find { element -> element.nome == nomeAnimal }
        if(resposta == null){
            return Animal("","",-1,"",false,false,false,"", "",
                Cliente("", Email(""))
            )
        }
        return resposta
    }

    fun getAllData() :MutableList<Animal>{
        //fila = repositorio.getAll()
        return fila
    }

    fun validarPedidoDados(animal: Animal):Boolean {
        if( !animal.cliente.email.isValid()){
            return false
        }else if( animal.cliente.nome.equals("")){
            return false
        }
        return true
    }
    fun mudarStatus(status:String, animal: Animal){
        animal.mudarStatus(status)
    }


}