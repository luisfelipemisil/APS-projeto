package com.app.myapplication.model.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.app.myapplication.model.entitie.CPF
import com.app.myapplication.model.entitie.Cartao
import com.app.myapplication.model.entitie.Email
import com.app.myapplication.model.entitie.User
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import kotlin.math.log

class IRepositorioUsuario : RepositorioUsuario {

    private val url = "jdbc:postgresql://babar.db.elephantsql.com:5432/dlaqohit"
    private val usuario = "dlaqohit"
    private val senha = "xUqzNjU9ifcv4zeV5pms9Z7di1u0U3O0"
    private var conection :Connection? =null

    fun iniciarConexao(): Boolean{

        try {
            Class.forName("org.postgresql.Driver")
            conection = DriverManager.getConnection(url, usuario, senha)
            return true
        } catch (e: SQLException) {
            throw RuntimeException("Erro ao conectar ao banco de dados", e)
        }
    }

    override fun setup(): Boolean {
        return iniciarConexao()
    }

    override fun addUsuario(s: User): Boolean {
        TODO()
    }


    override fun removerUsuario(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun findUsuario(email: String): Pair<Boolean, User> {
        TODO("Not yet implemented")
    }

    override fun editUsuario(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAll(): MutableList<User> {
        val SQL = "select * from users"
        var users = mutableListOf<User>()
        try {
            this.conection.use { conn ->
                if (conn != null) {
                    conn.prepareStatement(SQL).use { pstmt ->
                        val rs: ResultSet = pstmt.executeQuery()
                        while (rs.next()) {
                            System.out.println("getAll: nome: ${rs.getString("nome")} senha: ${rs.getString("senha")} senha: ${rs.getString("senha")} email: ${rs.getString("email")} cartao_numero: ${rs.getString("cartao_numero")} cartao_validade: ${rs.getString("cartao_validade")} cartao_cod: ${rs.getString("cartao_cod")} cpf: ${rs.getString("cpf")}")
                            users.add(User(rs.getString("nome"), rs.getString("senha"), rs.getString("senha"), Email(rs.getString("email")), Cartao(rs.getString("cartao_numero"), rs.getString("cartao_validade"), rs.getString("cartao_cod")), CPF(rs.getString("cpf")) ))
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            print(e.message)
        }
        return users
    }
}