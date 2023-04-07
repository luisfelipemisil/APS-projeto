package com.app.myapplication.model.repository

class fabricaRepositorioFilaCastracao:AbstractFactory {

    override fun repositorioFilaCastracao(): RepositorioFilaCastracao {
        return IRepositorioFilaCastracao()
    }

    override fun repositorioUsuario(): RepositorioUsuario {
        return IRepositorioUsuario()
    }
}