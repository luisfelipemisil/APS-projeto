package com.app.myapplication.model.repository

import android.content.Context

interface AbstractFactory {
    fun repositorioUsuario(): RepositorioUsuario
    fun repositorioPedidos(): RepositorioPedidos

}