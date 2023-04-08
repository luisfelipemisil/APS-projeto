package com.app.myapplication.controller

import android.content.Intent
import android.graphics.Color
import androidx.core.view.isVisible
import com.app.myapplication.model.entitie.CPF
import com.app.myapplication.model.entitie.Cartao
import com.app.myapplication.model.entitie.Email
import com.app.myapplication.model.entitie.User
import com.app.myapplication.view.ADM
import com.app.myapplication.view.TelaHome
import com.app.myapplication.view.TelaPedido
import com.app.myapplication.view.TelaUsuario

class ControllerTelaHome(tela: TelaHome) {
    var telaDoacao: TelaHome = tela
    private var isLoginTela = false
    val fachada = Fachada(tela)
    fun setup(){
        val (ok, user) = fachada.hasUser()
        if (ok){
            this.telaDoacao.startActivity(Intent(telaDoacao, TelaUsuario::class.java).putExtra("nome", user.nome))
        }
        this.telaDoacao.BotaoVoltarCadastro.isVisible = true
        this.telaDoacao.botaoPedidos.isVisible = true
        this.telaDoacao.botaoLogin.isVisible = true
        this.telaDoacao.cadastroNome.isVisible = false
        this.telaDoacao.cadastroEmail.isVisible = false
        this.telaDoacao.cadastroSenha.isVisible = false
        this.telaDoacao.cadastroSegundaSenha.isVisible = false
        this.telaDoacao.BotaoConfirmarCadastro.isVisible = false
        this.telaDoacao.cadastroMsg.isVisible = false
        this.telaDoacao.BotaoVoltarCadastro.isVisible = false
        this.telaDoacao.BotaoCadastro.isVisible = true
        this.telaDoacao.cadastroCPF.isVisible = false
        this.telaDoacao.cadastroCartaoNumero.isVisible = false
        this.telaDoacao.cadastroCartaoValidade.isVisible= false
        this.telaDoacao.cadastroCartaoCod.isVisible = false

        this.telaDoacao.cadastroEmail.setText("")
        this.telaDoacao.cadastroNome.setText("")
        this.telaDoacao.cadastroSenha.setText("")
        this.telaDoacao.cadastroSegundaSenha.setText("")
        this.telaDoacao.cadastroCPF.setText("")
        this.telaDoacao.cadastroCartaoNumero.setText("")
        this.telaDoacao.cadastroCartaoValidade.setText("")
        this.telaDoacao.cadastroCartaoCod.setText("")

    }
    fun saySomething(){
        this.telaDoacao.cadastroMsg.setText("Olá")
    }

    fun goToPedidos(){
        this.telaDoacao.startActivity(Intent(telaDoacao, TelaPedido::class.java))
    }

    fun goToADM(){
        this.telaDoacao.startActivity(Intent(telaDoacao, ADM::class.java))
    }

    fun telaLogin(){
        this.telaDoacao.cadastroEmail.isVisible = true
        this.telaDoacao.cadastroSenha.isVisible = true
        this.telaDoacao.cadastroMsg.isVisible = true
        this.telaDoacao.BotaoVoltarCadastro.isVisible = true
        this.telaDoacao.botaoPedidos.isVisible = false
        this.telaDoacao.botaoLogin.isVisible = false
        this.telaDoacao.BotaoConfirmarCadastro.isVisible = true
        isLoginTela = true
    }

    fun telaCadastro(){
        this.telaDoacao.cadastroNome.isVisible = true
        this.telaDoacao.cadastroEmail.isVisible = true
        this.telaDoacao.cadastroSenha.isVisible = true
        this.telaDoacao.cadastroSegundaSenha.isVisible = true
        this.telaDoacao.cadastroMsg.isVisible = true
        this.telaDoacao.BotaoVoltarCadastro.isVisible = true
        this.telaDoacao.botaoPedidos.isVisible = false
        this.telaDoacao.botaoLogin.isVisible = false
        this.telaDoacao.BotaoCadastro.isVisible = false
        this.telaDoacao.BotaoConfirmarCadastro.isVisible = true
        this.telaDoacao.cadastroCPF.isVisible = true
        this.telaDoacao.cadastroCartaoNumero.isVisible = true
        this.telaDoacao.cadastroCartaoValidade.isVisible= true
        this.telaDoacao.cadastroCartaoCod.isVisible = true
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
        val nome = this.telaDoacao.cadastroNome.text.toString()
        val email_ender = this.telaDoacao.cadastroEmail.text.toString()
        val primeira_senha = this.telaDoacao.cadastroSenha.text.toString()
        val segunda_senha = this.telaDoacao.cadastroSegundaSenha.text.toString()
        val registro = this.telaDoacao.cadastroCPF.text.toString()
        val cartaoNumero = this.telaDoacao.cadastroCartaoNumero.text.toString()
        val cartaoValidade = this.telaDoacao.cadastroCartaoValidade.text.toString()
        val cartaoCod = this.telaDoacao.cadastroCartaoCod.text.toString()

        val cartao = Cartao(cartaoNumero, cartaoValidade, cartaoCod)
        val email = Email(email_ender)
        val cpf  = CPF(registro)

        val resposta = fachada.addUsuario(User(nome, primeira_senha, segunda_senha, email,cartao, cpf))

        if (!resposta){
            this.telaDoacao.cadastroMsg.setTextColor(Color.RED)
            this.telaDoacao.cadastroMsg.setText("ERRO, tem algum dado errado")
        }else{
            this.telaDoacao.startActivity(Intent(telaDoacao, TelaUsuario::class.java).putExtra("nome",nome))
        }
    }

    private fun loginUsuario(){

        val email_ender = this.telaDoacao.cadastroEmail.text.toString()
        val primeira_senha = this.telaDoacao.cadastroSenha.text.toString()
        val email = Email(email_ender)

        val resposta = fachada.validarUsuario(User("", primeira_senha, "", email,Cartao("","",""), CPF("")))

        if (!resposta.first){
            this.telaDoacao.cadastroMsg.setTextColor(Color.RED)
            this.telaDoacao.cadastroMsg.setText("ERRO, tem algum dado errado")
        }else{
            this.telaDoacao.startActivity(Intent(telaDoacao, TelaUsuario::class.java).putExtra("nome",resposta.second.nome))
        }
    }

}