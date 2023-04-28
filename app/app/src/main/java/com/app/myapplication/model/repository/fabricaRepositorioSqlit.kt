package com.app.myapplication.model.repository

class fabricaRepositorioSqlit:AbstractFactory {

    override fun repositorioUsuario(): RepositorioUsuario {
        return IRepositorioUsuariosSQLite()
    }

    override fun repositorioPedidos(): RepositorioPedidos {
        return IReposirotioPedidosSQLite()
    }


}