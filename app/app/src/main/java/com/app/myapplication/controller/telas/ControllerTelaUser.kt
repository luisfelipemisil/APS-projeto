package com.app.myapplication.controller.telas

import android.content.Intent
import com.app.myapplication.controller.Fachada
import com.app.myapplication.view.TelaDoar
import com.app.myapplication.view.TelaPedido
import com.app.myapplication.view.TelaUsuario

class ControllerTelaUser(tela: TelaUsuario) {
    var telaPedido = tela
    val fachada = Fachada(tela)

    fun voltar(){
        this.telaPedido.finish()
    }

    fun goToPedidos(){
        this.telaPedido.startActivity(Intent(telaPedido, TelaPedido::class.java))
    }

    fun goToDoar(){
        this.telaPedido.startActivity(Intent(telaPedido, TelaDoar::class.java))
    }
}