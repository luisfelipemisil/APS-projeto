package com.app.myapplication.model.repository

import android.content.Context

class fabricaRepositorioFilaCastracao:AbstractFactory {

    override fun repositorioFilaCastracao(): RepositorioFilaCastracao {
        return IRepositorioFilaCastracao()
    }

    override fun repositorioUsuario(): RepositorioUsuario {
        return IRepositorioUsuario()
    }

    override fun repositorioUsuarioSQLite(context: Context): RepositorioUsuario {
        return IRepositorioUsuariosSQLite(context)
    }

    override fun repositorioPedidosSQLite(context: Context): RepositorioPedidos {
        return IReposirotioPedidosSQLite(context)
    }
}