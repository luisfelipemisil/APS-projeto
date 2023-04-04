package com.app.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.app.myapplication.R

class TelaUsuario : AppCompatActivity() {
    lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_usuario)
        title = findViewById(R.id.userTitle)
        val nome = intent.getStringExtra( "nome")
        if (nome != null){
            title.setText(nome)
        }
    }
}