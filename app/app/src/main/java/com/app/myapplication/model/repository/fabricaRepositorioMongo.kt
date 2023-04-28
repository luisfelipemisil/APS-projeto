package com.app.myapplication.model.repository

class fabricaRepositorioMongo: AbstractFactory {
    override fun repositorioUsuario(): RepositorioUsuario {
        return IRepositorioUsuario()
    }

    override fun repositorioPedidos(): RepositorioPedidos {
        return IRepositorioFilaCastracao()
    }
}