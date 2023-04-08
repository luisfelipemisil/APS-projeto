package com.app.myapplication.model.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.app.myapplication.model.entitie.CPF
import com.app.myapplication.model.entitie.Cartao
import com.app.myapplication.model.entitie.Email
import com.app.myapplication.model.entitie.User

class IRepositorioUsuariosSQLite():RepositorioUsuario {

    lateinit var context: Context
    private lateinit var dbHelper : SQLite

    constructor(context: Context) : this() {

        this.context = context

    }

    override fun setup(): Boolean {
        return true
    }

    override fun addUsuario(user: User): Boolean {
        // Gets the data repository in write mode
        val dbHelper = SQLite(context)
        val db = dbHelper.writableDatabase
        if(db != null){
            val values = ContentValues().apply {
                put(SQLite.NOME, user.nome)
                put(SQLite.SENHA, user.senha)
                put(SQLite.EMAIL, user.email.endereco)
                put(SQLite.CPF, user.cpf.registro)
                put(SQLite.CARTAO_NUMERO, user.cartao.numero)
                put(SQLite.CARTAO_VALIDADE, user.cartao.validade)
                put(SQLite.CARTAO_COD, user.cartao.codigo)
            }
            // Insert the new row, returning the primary key value of the new row
            val newRowId = db.insert(SQLite.TABLE_USER, null, values)
            return (newRowId > 0)
        }
        return false

    }

    override fun removerUsuario(email: String): Boolean {
        val db = dbHelper.writableDatabase
        // Define 'where' part of query.
        val selection = "${SQLite.EMAIL} LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(email)
        // Issue SQL statement.
        val deletedRows = db.delete(SQLite.TABLE_USER, selection, selectionArgs)

        return( deletedRows > 0)
    }

    override fun findUsuario(email: String): Pair<Boolean, User> {
        val dbHelper = SQLite(context )
        val db = dbHelper.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(BaseColumns._ID, SQLite.EMAIL, SQLite.SENHA)

        // Filter results WHERE "title" = 'My Title'
        val selection = "${SQLite.EMAIL} = ?"
        val selectionArgs = arrayOf(email)

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${SQLite.EMAIL} DESC"

        val cursor = db.query(
            SQLite.TABLE_USER,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val users = mutableListOf<User>()
        with(cursor) {
            while (moveToNext()) {
                val nome = getString(getColumnIndexOrThrow(SQLite.NOME))
                val senha = getString(getColumnIndexOrThrow(SQLite.SENHA))
                val cpf = getString(getColumnIndexOrThrow(SQLite.CPF))
                val email_sql = getString(getColumnIndexOrThrow(SQLite.EMAIL))
                val cartao_num = getString(getColumnIndexOrThrow(SQLite.CARTAO_NUMERO))
                val cartao_val = getString(getColumnIndexOrThrow(SQLite.CARTAO_VALIDADE))
                val cartao_cod = getString(getColumnIndexOrThrow(SQLite.CARTAO_COD))
                val user_ = User(nome, senha, senha, Email(email_sql), Cartao(cartao_num, cartao_val, cartao_cod), CPF(cpf))
                users.add(user_)
            }
        }
        cursor.close()
        if(users.isNotEmpty()){
            return Pair(true, users.first() )
        }
        return Pair(false, User() )
    }

    override fun editUsuario(user: User): Boolean {
        val db = dbHelper.writableDatabase

        // New value for one column
        val values = ContentValues().apply {
            put(SQLite.NOME, user.nome)
            put(SQLite.SENHA, user.senha)
            put(SQLite.CARTAO_NUMERO, user.cartao.numero)
            put(SQLite.CARTAO_VALIDADE, user.cartao.validade)
            put(SQLite.CARTAO_COD, user.cartao.codigo)
        }

        // Which row to update, based on the title
        val selection = "${SQLite.EMAIL} LIKE ?"
        val selectionArgs = arrayOf(user.email.endereco)
        val count = db.update(
            SQLite.TABLE_USER,
            values,
            selection,
            selectionArgs)

        return (count > 0)
    }

    override fun getAll(): MutableList<User> {
        val dbHelper = SQLite(context )
        val db = dbHelper.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(BaseColumns._ID, SQLite.EMAIL, SQLite.SENHA, SQLite.NOME, SQLite.CPF, SQLite.CARTAO_VALIDADE, SQLite.CARTAO_NUMERO, SQLite.CARTAO_COD)

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${SQLite.EMAIL} DESC"

        val cursor = db.query(
            SQLite.TABLE_USER,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val users = mutableListOf<User>()
        with(cursor) {
            while (moveToNext()) {
                val nome = getString(getColumnIndexOrThrow(SQLite.NOME))
                val senha = getString(getColumnIndexOrThrow(SQLite.SENHA))
                val cpf = getString(getColumnIndexOrThrow(SQLite.CPF))
                val email_ = getString(getColumnIndexOrThrow(SQLite.EMAIL))
                val cartao_num = getString(getColumnIndexOrThrow(SQLite.CARTAO_NUMERO))
                val cartao_val = getString(getColumnIndexOrThrow(SQLite.CARTAO_VALIDADE))
                val cartao_cod = getString(getColumnIndexOrThrow(SQLite.CARTAO_COD))
                if(nome != null && senha != null && cpf != null && email_ != null && cartao_num != null && cartao_val != null && cartao_cod != null) {
                    val user_ = User(
                        nome,
                        senha,
                        senha,
                        Email(email_),
                        Cartao(cartao_num, cartao_val, cartao_cod),
                        CPF(cpf)
                    )
                    users.add(user_)
                }
            }
        }
        cursor.close()
        return users

    }




}