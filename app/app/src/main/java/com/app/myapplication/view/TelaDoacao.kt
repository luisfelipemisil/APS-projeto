package com.app.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.app.myapplication.R
import com.app.myapplication.controller.ControllerTelaDoacao

class TelaDoacao : AppCompatActivity() {

    lateinit var botaoPedidos: Button
    lateinit var botaoLogin: Button
    lateinit var BotaoCadastro: Button
    lateinit var BotaoConfirmarCadastro: Button
    lateinit var BotaoVoltarCadastro: Button

    lateinit var cadastroNome: TextView
    lateinit var cadastroEmail: TextView
    lateinit var cadastroSenha:TextView
    lateinit var cadastroSegundaSenha: TextView
    lateinit var cadastroCPF: TextView
    lateinit var cadastroCartaoNumero: TextView
    lateinit var cadastroCartaoValidade: TextView
    lateinit var cadastroCartaoCod:TextView

    lateinit var cadastroMsg: TextView

    lateinit var controllerDoacao: ControllerTelaDoacao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_home)

        botaoPedidos = findViewById(R.id.botaoPedidos)
        botaoLogin = findViewById(R.id.botaoCadastroLogin)
        BotaoCadastro = findViewById(R.id.botaoCadastro)
        BotaoConfirmarCadastro = findViewById(R.id.botaoConfirmarCadastro)
        BotaoVoltarCadastro = findViewById(R.id.botaoVoltarCadastro)

        cadastroNome = findViewById(R.id.cadastroNome)
        cadastroEmail = findViewById(R.id.cadastroEmail)
        cadastroSenha = findViewById(R.id.cadastroSenha)
        cadastroSegundaSenha = findViewById(R.id.cadastroSegundaSenha)
        cadastroCPF = findViewById(R.id.cadastroCPF)

        cadastroCartaoNumero = findViewById(R.id.cadastroCartaoNumero)
        cadastroCartaoValidade = findViewById(R.id.cadastroCartaoValidade)
        cadastroCartaoCod = findViewById(R.id.cadastroCartaoCod)

        cadastroMsg = findViewById(R.id.cadastroMsg)

        controllerDoacao = ControllerTelaDoacao(this)

        controllerDoacao.setup()

        botaoPedidos.setOnClickListener {
            controllerDoacao.goToPedidos()
        }

        botaoLogin.setOnClickListener {
            controllerDoacao.login()
        }

        BotaoCadastro.setOnClickListener {
            controllerDoacao.cadastro()
        }

        BotaoConfirmarCadastro.setOnClickListener {
            controllerDoacao.cadastrarUsuario()
        }

        BotaoVoltarCadastro.setOnClickListener {
            controllerDoacao.setup()
        }
    }
}