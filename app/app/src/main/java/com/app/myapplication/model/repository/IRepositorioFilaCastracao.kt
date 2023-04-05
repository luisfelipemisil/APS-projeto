package com.app.myapplication.model.repository

import com.app.myapplication.model.entitie.Animal
import com.app.myapplication.model.entitie.CPF
import com.app.myapplication.model.entitie.User

class IRepositorioFilaCastracao: RepositorioFilaCastracao {


    override fun editAnimal(animal: Animal): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAll(): MutableList<Animal> {
        TODO("Not yet implemented")
    }

    override fun findAnimal(animal: Animal): Boolean {
        TODO("Not yet implemented")
    }

    override fun addAnimal(animal: Animal): Boolean {
        return false
    }

    override fun removerAnimal(animal: Animal): Boolean {
        TODO("Not yet implemented")
    }

}