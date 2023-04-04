package com.app.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.app.myapplication.R
import com.app.myapplication.controller.ControllerTelaPedido
import org.w3c.dom.Text

class TelaPedido : AppCompatActivity() {
    lateinit var nomeCliente: TextView
    lateinit var emailCliente: TextView
    lateinit var nomeAnimal: TextView
    lateinit var idadeAnimal: TextView
    lateinit var racaAnimal: TextView
    lateinit var gato : CheckBox
    lateinit var cachorro: CheckBox
    lateinit var filhote: CheckBox
    lateinit var enderecoAnimal: TextView
    lateinit var descricaoAnimal: TextView
    lateinit var msg : TextView

    lateinit var botaoEnviar : Button
    lateinit var botaoVoltar : Button

    lateinit var controllerPedido :ControllerTelaPedido
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_pedido)

        nomeAnimal = findViewById(R.id.pedidoNomeAnimal)
        emailCliente = findViewById(R.id.pedidoClienteEmail)
        nomeCliente = findViewById(R.id.pedidoClienteNome)
        idadeAnimal = findViewById(R.id.pedidoIdadeAnimal)
        racaAnimal = findViewById(R.id.pedidoRacaAnimal)
        gato = findViewById(R.id.pedidoCheckBoxGato)
        cachorro = findViewById(R.id.pedidoCheckBoxCachorro)
        filhote = findViewById(R.id.pedidoCheckBoxFilhote)
        enderecoAnimal = findViewById(R.id.pedidoEnderecoResgate)
        descricaoAnimal = findViewById(R.id.pedidoEstadoAnimal)
        botaoEnviar = findViewById(R.id.pedidoBotaoEnviar)
        botaoVoltar = findViewById(R.id.pedidoBotaoVoltar)
        msg = findViewById(R.id.pedidoMsg)

        controllerPedido = ControllerTelaPedido(this)

        botaoEnviar.setOnClickListener {
            controllerPedido.enviarPedido()
        }

        botaoVoltar.setOnClickListener {
            controllerPedido.voltar()
        }

        gato.setOnClickListener {
            controllerPedido.checkGato()
        }

        cachorro.setOnClickListener{
            controllerPedido.checkCachorro()
        }

    }
}