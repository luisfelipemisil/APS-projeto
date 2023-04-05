package com.app.myapplication.controller

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import com.app.myapplication.model.collection.FilaCastracao
import com.app.myapplication.model.entitie.Animal
import com.app.myapplication.model.entitie.Cliente
import com.app.myapplication.model.entitie.Email
import com.app.myapplication.view.TelaPedido
import com.app.myapplication.view.TelaUsuario

class ControllerTelaPedido(tela: TelaPedido) {
    var telaPedido = tela

    lateinit var nomeCliente :String
    lateinit var emailCliente :String
    lateinit var nomeAnimal :  String
    lateinit var idadeAnimal :String
    lateinit var racaAnimal :String
    lateinit var estadoAnimal :String
    var gato  = false
    var cachorro = false
    var filhote = false
    lateinit var endereco :String

    fun saySomething(){
        this.telaPedido.msg.setText("Olá")
    }

    fun checkGato(){
        this.telaPedido.cachorro.isChecked = false
        this.telaPedido.gato.isChecked = true
    }

    fun checkCachorro(){
        this.telaPedido.gato.isChecked = false
        this.telaPedido.cachorro.isChecked = true
    }

    fun voltar(){
        this.telaPedido.finish()
    }

    fun enviarPedido(){
        nomeCliente = this.telaPedido.nomeCliente.text.toString()
        emailCliente = this.telaPedido.emailCliente.text.toString()
        nomeAnimal = this.telaPedido.nomeAnimal.text.toString()
        idadeAnimal = this.telaPedido.idadeAnimal.text.toString()
        racaAnimal = this.telaPedido.racaAnimal.text.toString()
        estadoAnimal = this.telaPedido.descricaoAnimal.text.toString()
        gato = this.telaPedido.gato.isChecked
        cachorro = this.telaPedido.cachorro.isChecked
        filhote = this.telaPedido.filhote.isChecked
        endereco = this.telaPedido.enderecoAnimal.text.toString()
        val email = Email(emailCliente)
        val cliente = Cliente(nomeCliente, email)
        val animal = Animal(nomeAnimal, "em analise", idadeAnimal.toInt(), racaAnimal, gato, cachorro, filhote, endereco, estadoAnimal,cliente )
        val resposta = FilaCastracao.add(animal)

        if(!resposta){
            this.telaPedido.msg.setTextColor(Color.RED)
            this.telaPedido.msg.setText("Erro dados cliente")
        }else{
            this.telaPedido.startActivity(Intent(telaPedido, TelaUsuario::class.java).putExtra("nome", "Usuário"))
        }
    }
}