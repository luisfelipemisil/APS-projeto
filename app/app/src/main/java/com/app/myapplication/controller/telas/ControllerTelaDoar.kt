package com.app.myapplication.controller.telas

import android.content.Intent
import com.app.myapplication.controller.Fachada
import com.app.myapplication.view.TelaDoar
import com.app.myapplication.view.TelaHome
import com.app.myapplication.view.TelaPedido
import com.app.myapplication.view.TelaUsuario

class ControllerTelaDoar(tela: TelaDoar) {
    var telaDoacao: TelaDoar = tela
    val fachada = Fachada(tela)

    fun gotoBack(){
        this.telaDoacao.startActivity(Intent(telaDoacao, TelaUsuario::class.java))
    }

    fun doar(){
        val valor = this.telaDoacao.valor.text.toString()
        fachada.doar(valor.toDouble())
        // envia pra algum lugar
        this.telaDoacao.startActivity(Intent(telaDoacao, TelaUsuario::class.java))
    }

}