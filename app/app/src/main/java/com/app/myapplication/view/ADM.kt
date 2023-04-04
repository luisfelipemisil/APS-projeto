package com.app.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.app.myapplication.R
import com.app.myapplication.controller.ADMController

class ADM : AppCompatActivity() {

    lateinit var botaoUsuario : Button
    lateinit var botaoDoacao: Button
    lateinit var botaoAdoacao: Button
    lateinit var botaoPedido: Button
    lateinit var botaoFuncionario: Button
    lateinit var botaoVeterinario: Button
    lateinit var msgText: TextView

    lateinit var admController: ADMController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        botaoUsuario  = findViewById(R.id.botaoTCadasUsua)
        botaoDoacao = findViewById(R.id.botaoTDoacao)
        botaoAdoacao = findViewById(R.id.botaotAdocao)
        botaoPedido = findViewById(R.id.botaoTPedido)
        botaoFuncionario = findViewById(R.id.botaoTFuncionario)
        botaoVeterinario = findViewById(R.id.botaoTVeterinario)
        msgText = findViewById(R.id.msg)

        admController = ADMController(this)

        botaoAdoacao.setOnClickListener {
            admController.goToCadastroUsuario()
        }

        botaoUsuario.setOnClickListener {
            admController.goToCadastroUsuario()
        }

        botaoDoacao.setOnClickListener {
            admController.goToDoacao()
        }

        botaoPedido.setOnClickListener {
            admController.goToPedido()
        }

        botaoFuncionario.setOnClickListener {
            admController.goToFuncionario()
        }

        botaoVeterinario.setOnClickListener {
            admController.goToVeterinario()
        }
    }
}