package com.app.myapplication.controller

import android.content.Intent
import com.app.myapplication.view.*

class ADMController(tela: ADM) {

    var telaADM : ADM = tela

    fun saySomething(){
        this.telaADM.msgText.setText("Olá")
    }

    fun goToAdocao(){
        telaADM.startActivity(Intent(telaADM, TelaAdocao::class.java))
    }

    fun goToCadastroUsuario(){
        telaADM.startActivity(Intent(telaADM, TelaUsuario::class.java))
    }

    fun goToDoacao(){
        telaADM.startActivity(Intent(telaADM, TelaHome::class.java))
    }

    fun goToFuncionario(){
        telaADM.startActivity(Intent(telaADM, TelaFuncionario::class.java))
    }

    fun goToPedido(){
        telaADM.startActivity(Intent(telaADM, TelaPedido::class.java))
    }

    fun goToVeterinario(){
        telaADM.startActivity(Intent(telaADM, TelaDoar::class.java))
    }
}