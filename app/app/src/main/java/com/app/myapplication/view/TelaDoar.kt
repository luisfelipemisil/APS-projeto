package com.app.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.app.myapplication.R
import com.app.myapplication.controller.ControllerDoar
import com.app.myapplication.controller.telas.ControllerTelaDoar
import com.app.myapplication.controller.telas.ControllerTelaHome

class TelaDoar : AppCompatActivity() {
    lateinit var botaoVoltar: Button
    lateinit var BotaoDoar: Button
    lateinit var valor: TextView
    lateinit var controllerDoacao: ControllerTelaDoar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_doar)

        botaoVoltar = findViewById(R.id.botaoDoarVoltar)
        BotaoDoar = findViewById(R.id.botaoDoarConfirmar)
        valor = findViewById(R.id.valorDoar)
        controllerDoacao = ControllerTelaDoar(this)

        botaoVoltar.setOnClickListener {
            controllerDoacao.gotoBack()
        }

        BotaoDoar.setOnClickListener {
            controllerDoacao.doar()
        }
    }
}