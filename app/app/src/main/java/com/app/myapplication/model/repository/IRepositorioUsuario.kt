package com.app.myapplication.model.repository

import com.app.myapplication.model.entitie.CPF
import com.app.myapplication.model.entitie.Cartao
import com.app.myapplication.model.entitie.Email
import com.app.myapplication.model.entitie.User
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory

class IRepositorioUsuario() : RepositorioUsuario {


    private var conection :Connection? =null
    private var status = false


    suspend fun iniciarConexao(): Boolean{
        val url = "jdbc:postgresql://babar.db.elephantsql.com:5432/dlaqohit"
        val usuario = "dlaqohit"
        val senha = "xUqzNjU9ifcv4zeV5pms9Z7di1u0U3O0"
        try {
            conection = DriverManager.getConnection(url, usuario, senha)
            return true
        } catch (e: SQLException) {
            throw RuntimeException("Erro ao conectar ao banco de dados", e)
        }
    }

    override fun setup(): Boolean {
        TODO()
    }

    override fun addUsuario(user: User): Boolean {
        val SQL = "insert into users (cpf, senha, nome, email, cartao_numero, cartao_validade, cartao_cod) values('${user.cpf.registro}', '${user.senha}', '${user.nome}','${user.email.endereco}','${user.cartao.numero}','${user.cartao.validade}', '${user.cartao.codigo}')"
        val res =  runQueueUser(SQL)
        return res.first
    }

    override fun removerUsuario(email: String): Boolean {
        val SQL = "delete from users where email = '${email}'"
        val res =  runQueueUser(SQL)
        return res.first
    }

    override fun findUsuario(email: String): Pair<Boolean, User> {
        val SQL = "select * from users where email = '${email}'"
        val res =  runQueueUser(SQL)
        return Pair(res.first, res.second.first())
    }

    override fun editUsuario(user: User): Boolean {
        val SQL = "update users set cpf = '${user.cpf.registro}', senha = '${user.senha}', nome = '${user.nome}', cartao_numero = '${user.cartao.numero}', cartao_validade = '${user.cartao.validade}', cartao_cod = '${user.cartao.codigo}' WHERE email = '${user.email.endereco}'"
        val res =  runQueueUser(SQL)
        return res.first
    }

    override fun getAll(): MutableList<User> {
        val SQL = "select * from users"
        var (_, users) = runQueueUser(SQL)
        return users
    }

    private fun runQueueUser(queue:String):Pair<Boolean, MutableList<User>>{
        val users = mutableListOf<User>()
        try {
            this.conection.use { conn ->
                if (conn != null) {
                    conn.prepareStatement(queue).use { pstmt ->
                        val rs: ResultSet = pstmt.executeQuery()
                        while (rs.next()) {
                            System.out.println("nome: ${rs.getString("nome")} senha: ${rs.getString("senha")} senha: ${rs.getString("senha")} email: ${rs.getString("email")} cartao_numero: ${rs.getString("cartao_numero")} cartao_validade: ${rs.getString("cartao_validade")} cartao_cod: ${rs.getString("cartao_cod")} cpf: ${rs.getString("cpf")}")
                            users.add(User(rs.getString("nome"), rs.getString("senha"), rs.getString("senha"), Email(rs.getString("email")), Cartao(rs.getString("cartao_numero"), rs.getString("cartao_validade"), rs.getString("cartao_cod")), CPF(rs.getString("cpf")) ))
                        }
                        return Pair(true, users)
                    }
                }else{
                    return Pair(false, users)
                }
            }
        } catch (e: SQLException) {
            print("Error::  ${e.errorCode}")
            if(e.errorCode == 0){
                return Pair(true, users)
            }else if(e.errorCode.toString().contains("SUCCESSFUL")){
                return Pair(true, users)
            }
            return Pair(false, users)
        }
    }
}