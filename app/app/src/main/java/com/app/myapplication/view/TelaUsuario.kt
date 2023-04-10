package com.app.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.app.myapplication.R
import com.app.myapplication.controller.telas.ControllerTelaUser

class TelaUsuario : AppCompatActivity() {
    lateinit var title: TextView
    lateinit var botaoSair : Button
    lateinit var botaoPedido: Button
    lateinit var botaoDoar: Button
    lateinit var controllerDoacao: ControllerTelaUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_usuario)
        title = findViewById(R.id.userTitle)
        botaoSair  = findViewById(R.id.botaoSair)
        botaoPedido = findViewById(R.id.botaoPedidos)
        botaoDoar = findViewById(R.id.botaoDoacao)
        controllerDoacao = ControllerTelaUser(this  )

        val nome = intent.getStringExtra( "nome")
        if (nome != null){
            title.setText(nome)
        }

        botaoSair.setOnClickListener {
            controllerDoacao.voltar()
        }

        botaoPedido.setOnClickListener {
            controllerDoacao.goToPedidos()
        }

        botaoDoar.setOnClickListener {
            controllerDoacao.goToDoar()
        }

    }
}