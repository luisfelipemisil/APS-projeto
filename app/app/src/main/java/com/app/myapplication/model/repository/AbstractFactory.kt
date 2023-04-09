package com.app.myapplication.model.repository

import android.content.Context

interface AbstractFactory {
    fun repositorioFilaCastracao(): RepositorioFilaCastracao
    fun repositorioUsuario(): RepositorioUsuario
    fun repositorioUsuarioSQLite(context: Context): RepositorioUsuario

    fun repositorioPedidosSQLite(context: Context): RepositorioPedidos
}