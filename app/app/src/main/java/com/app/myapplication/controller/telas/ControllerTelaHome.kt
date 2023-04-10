package com.app.myapplication.controller.telas

import android.content.Intent
import android.graphics.Color
import androidx.core.view.isVisible
import com.app.myapplication.controller.Fachada
import com.app.myapplication.model.entitie.CPF
import com.app.myapplication.model.entitie.Cartao
import com.app.myapplication.model.entitie.Email
import com.app.myapplication.model.entitie.User
import com.app.myapplication.view.ADM
import com.app.myapplication.view.TelaHome
import com.app.myapplication.view.TelaPedido
import com.app.myapplication.view.TelaUsuario

class ControllerTelaHome(tela: TelaHome) {
    var telaHome: TelaHome = tela
    companion object{
        var isLoginTela = false
    }
    val fachada = Fachada(tela)
    fun setup(){
        val (ok, user) = fachada.hasUser()
        if (ok){
            this.telaHome.startActivity(Intent(telaHome, TelaUsuario::class.java).putExtra("nome", user.nome))
        }
        this.telaHome.BotaoVoltarCadastro.isVisible = true
        this.telaHome.botaoPedidos.isVisible = true
        this.telaHome.botaoLogin.isVisible = true
        this.telaHome.cadastroNome.isVisible = false
        this.telaHome.cadastroEmail.isVisible = false
        this.telaHome.cadastroSenha.isVisible = false
        this.telaHome.cadastroSegundaSenha.isVisible = false
        this.telaHome.BotaoConfirmarCadastro.isVisible = false
        this.telaHome.cadastroMsg.isVisible = false
        this.telaHome.BotaoVoltarCadastro.isVisible = false
        this.telaHome.BotaoCadastro.isVisible = true
        this.telaHome.cadastroCPF.isVisible = false
        this.telaHome.cadastroCartaoNumero.isVisible = false
        this.telaHome.cadastroCartaoValidade.isVisible= false
        this.telaHome.cadastroCartaoCod.isVisible = false

        this.telaHome.cadastroEmail.setText("")
        this.telaHome.cadastroNome.setText("")
        this.telaHome.cadastroSenha.setText("")
        this.telaHome.cadastroSegundaSenha.setText("")
        this.telaHome.cadastroCPF.setText("")
        this.telaHome.cadastroCartaoNumero.setText("")
        this.telaHome.cadastroCartaoValidade.setText("")
        this.telaHome.cadastroCartaoCod.setText("")

    }
    fun saySomething(){
        this.telaHome.cadastroMsg.setText("Olá")
    }

    fun goToPedidos(){
        this.telaHome.startActivity(Intent(telaHome, TelaPedido::class.java))
    }

    fun goToADM(){
        this.telaHome.startActivity(Intent(telaHome, ADM::class.java))
    }

    fun telaLogin(){
        this.telaHome.cadastroEmail.isVisible = true
        this.telaHome.cadastroSenha.isVisible = true
        this.telaHome.cadastroMsg.isVisible = true
        this.telaHome.BotaoVoltarCadastro.isVisible = true
        this.telaHome.botaoPedidos.isVisible = false
        this.telaHome.botaoLogin.isVisible = false
        this.telaHome.BotaoConfirmarCadastro.isVisible = true
        isLoginTela = true
    }

    fun telaCadastro(){
        this.telaHome.cadastroNome.isVisible = true
        this.telaHome.cadastroEmail.isVisible = true
        this.telaHome.cadastroSenha.isVisible = true
        this.telaHome.cadastroSegundaSenha.isVisible = true
        this.telaHome.cadastroMsg.isVisible = true
        this.telaHome.BotaoVoltarCadastro.isVisible = true
        this.telaHome.botaoPedidos.isVisible = false
        this.telaHome.botaoLogin.isVisible = false
        this.telaHome.BotaoCadastro.isVisible = false
        this.telaHome.BotaoConfirmarCadastro.isVisible = true
        this.telaHome.cadastroCPF.isVisible = true
        this.telaHome.cadastroCartaoNumero.isVisible = true
        this.telaHome.cadastroCartaoValidade.isVisible= true
        this.telaHome.cadastroCartaoCod.isVisible = true
        isLoginTela = false
    }

    fun confirma(){
        if(isLoginTela){
            loginUsuario()
        }else{
            cadastrarUsuario()
        }
    }

    private fun cadastrarUsuario(){
        val nome = this.telaHome.cadastroNome.text.toString()
        val email_ender = this.telaHome.cadastroEmail.text.toString()
        val primeira_senha = this.telaHome.cadastroSenha.text.toString()
        val segunda_senha = this.telaHome.cadastroSegundaSenha.text.toString()
        val registro = this.telaHome.cadastroCPF.text.toString()
        val cartaoNumero = this.telaHome.cadastroCartaoNumero.text.toString()
        val cartaoValidade = this.telaHome.cadastroCartaoValidade.text.toString()
        val cartaoCod = this.telaHome.cadastroCartaoCod.text.toString()

        val cartao = Cartao(cartaoNumero, cartaoValidade, cartaoCod)
        val email = Email(email_ender)
        val cpf  = CPF(registro)

        val resposta = fachada.addUsuario(User(nome, primeira_senha, segunda_senha, email,cartao, cpf))

        if (!resposta){
            this.telaHome.cadastroMsg.setTextColor(Color.RED)
            this.telaHome.cadastroMsg.setText("ERRO, tem algum dado errado")
        }else{
            this.telaHome.startActivity(Intent(telaHome, TelaUsuario::class.java).putExtra("nome",nome))

        }
    }

    private fun loginUsuario(){

        val email_ender = this.telaHome.cadastroEmail.text.toString()
        val primeira_senha = this.telaHome.cadastroSenha.text.toString()
        val email = Email(email_ender)

        val resposta = fachada.validarUsuario(User("", primeira_senha, "", email,Cartao("","",""), CPF("")))

        if (!resposta.first){
            this.telaHome.cadastroMsg.setTextColor(Color.RED)
            this.telaHome.cadastroMsg.setText("ERRO, tem algum dado errado")
        }else{
            this.telaHome.startActivity(Intent(telaHome, TelaUsuario::class.java).putExtra("nome",resposta.second.nome))

        }
    }

}