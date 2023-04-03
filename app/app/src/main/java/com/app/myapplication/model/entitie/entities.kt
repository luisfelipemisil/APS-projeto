package com.app.myapplication.model.entitie

data class Animal(
 var nome:String,
 var status: String,
 var idade: Int,
 var raça: String,
 var filhote: Boolean,
 var endereço: String,
 var cliete: Clientes
 ){
    fun mudarStatus(status: String){
        this.status = status
    }

    fun notificarCliente(status: String) {
        // enviar email
        print(cliete.email.endereco)
    }
}

data class Doacao(
    val id:Int,
    var valor: Int,
    var descricao: String
)

data class User(
    var nome: String,
    var senha: String,
    var email: Email,
    var cartao: Cartao
)

data class Cuidado(
    var nomeEquipe: String,
    var qtdMembros: Int,
    var email: Email
)

data class Resgate(
    var nome:String,
    var qtdMembros: Int,
    var email: Email
)

data class Clientes(
    var nome: String,
    var email: Email
)

data class Veterinario(
    var nome: String,
    var cpf: Int,
    var email: Email
)

data class EquipeAnaliseDoacao(
    var nome: String,
    var qtdMembros: Int,
    var email: Email
)

data class Email(var endereco: String) {

    private val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    fun isValidEmail(): Boolean {
        return endereco.matches(regex.toRegex())
    }
}

data class Cartao (
    var numero :String,
    var validade: String,
    var codigo: String ){

    fun isValid(): Boolean {
        var s1 = 0
        var s2 = 0
        val reverse = StringBuffer(numero).reverse().toString()
        for (i in reverse.indices) {
            val digit = Character.digit(reverse[i], 10)
            when {
                i % 2 == 0 -> s1 += digit
                else -> {
                    s2 += 2 * digit
                    when {
                        digit >= 5 -> s2 -= 9
                    }
                }
            }
        }
        return (s1 + s2) % 10 == 0
    }

    companion object {
        private const val DECIMAL_BASE = 10
    }
}

