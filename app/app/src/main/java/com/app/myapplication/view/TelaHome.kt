package com.app.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.app.myapplication.R
import com.app.myapplication.controller.ControllerTelaHome

class TelaHome : AppCompatActivity() {

    lateinit var botaoPedidos: Button
    lateinit var botaoLogin: Button
    lateinit var BotaoCadastro: Button
    lateinit var BotaoConfirmarCadastro: Button
    lateinit var BotaoVoltarCadastro: Button
    lateinit var BotaoADM: Button

    lateinit var cadastroNome: TextView
    lateinit var cadastroEmail: TextView
    lateinit var cadastroSenha:TextView
    lateinit var cadastroSegundaSenha: TextView
    lateinit var cadastroCPF: TextView
    lateinit var cadastroCartaoNumero: TextView
    lateinit var cadastroCartaoValidade: TextView
    lateinit var cadastroCartaoCod:TextView

    lateinit var cadastroMsg: TextView

    lateinit var controllerDoacao: ControllerTelaHome



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_home)

        botaoPedidos = findViewById(R.id.botaoPedidos)
        botaoLogin = findViewById(R.id.botaoCadastroLogin)
        BotaoCadastro = findViewById(R.id.botaoCadastro)
        BotaoConfirmarCadastro = findViewById(R.id.botaoConfirmarCadastro)
        BotaoVoltarCadastro = findViewById(R.id.botaoVoltarCadastro)
        BotaoADM = findViewById(R.id.botaoADM)

        cadastroNome = findViewById(R.id.cadastroNome)
        cadastroEmail = findViewById(R.id.cadastroEmail)
        cadastroSenha = findViewById(R.id.cadastroSenha)
        cadastroSegundaSenha = findViewById(R.id.cadastroSegundaSenha)
        cadastroCPF = findViewById(R.id.cadastroCPF)

        cadastroCartaoNumero = findViewById(R.id.cadastroCartaoNumero)
        cadastroCartaoValidade = findViewById(R.id.cadastroCartaoValidade)
        cadastroCartaoCod = findViewById(R.id.cadastroCartaoCod)

        cadastroMsg = findViewById(R.id.cadastroMsg)

        controllerDoacao = ControllerTelaHome(this)

        controllerDoacao.setup()

        botaoPedidos.setOnClickListener {
            controllerDoacao.goToPedidos()
        }

        botaoLogin.setOnClickListener {
            controllerDoacao.telaLogin()

        }

        BotaoCadastro.setOnClickListener {
            controllerDoacao.telaCadastro()
        }

        BotaoConfirmarCadastro.setOnClickListener {
            controllerDoacao.confirma()

        }

        BotaoVoltarCadastro.setOnClickListener {
            controllerDoacao.setup()
        }

        BotaoADM.setOnClickListener {
            controllerDoacao.goToADM()
        }
    }
}