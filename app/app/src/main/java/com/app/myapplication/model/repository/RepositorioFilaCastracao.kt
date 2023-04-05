package com.app.myapplication.model.repository

import com.app.myapplication.model.entitie.Animal
import com.app.myapplication.model.entitie.User

interface RepositorioFilaCastracao {

    fun addAnimal(animal: Animal):Boolean

    fun removerAnimal(animal: Animal):Boolean

    fun findAnimal(animal: Animal):Boolean

    fun editAnimal(animal: Animal):Boolean

    fun getAll():MutableList<Animal>
}