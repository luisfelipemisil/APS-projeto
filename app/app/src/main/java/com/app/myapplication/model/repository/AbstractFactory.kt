package com.app.myapplication.model.repository

interface AbstractFactory {
    fun repositorioFilaCastracao(): RepositorioFilaCastracao
    fun repositorioUsuario(): RepositorioUsuario
}