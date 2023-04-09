package com.app.myapplication.model.repository

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.app.myapplication.model.entitie.CPF
import com.app.myapplication.model.entitie.Cartao
import com.app.myapplication.model.entitie.Email
import com.app.myapplication.model.entitie.User

class IRepositorioUsuariosSQLite():RepositorioUsuario {

    lateinit var context: Context
    private lateinit var dbHelper : SQLiteUser

    constructor(context: Context) : this() {

        this.context = context

    }

    override fun setup(): Boolean {
        return true
    }

    override fun addUsuario(user: User): Boolean {
        // Gets the data repository in write mode
        val dbHelper = SQLiteUser(context)
        val db = dbHelper.writableDatabase
        if(db != null){
            val values = ContentValues().apply {
                put(SQLiteUser.NOME, user.nome)
                put(SQLiteUser.SENHA, user.senha)
                put(SQLiteUser.EMAIL, user.email.endereco)
                put(SQLiteUser.CPF, user.cpf.registro)
                put(SQLiteUser.CARTAO_NUMERO, user.cartao.numero)
                put(SQLiteUser.CARTAO_VALIDADE, user.cartao.validade)
                put(SQLiteUser.CARTAO_COD, user.cartao.codigo)
            }
            // Insert the new row, returning the primary key value of the new row
            val newRowId = db.insert(SQLiteUser.TABLE_USER, null, values)
            return (newRowId > 0)
        }
        return false

    }

    override fun removerUsuario(email: String): Boolean {
        val db = dbHelper.writableDatabase
        // Define 'where' part of query.
        val selection = "${SQLiteUser.EMAIL} LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(email)
        // Issue SQL statement.
        val deletedRows = db.delete(SQLiteUser.TABLE_USER, selection, selectionArgs)

        return( deletedRows > 0)
    }

    override fun findUsuario(email: String): Pair<Boolean, User> {
        val dbHelper = SQLiteUser(context )
        val db = dbHelper.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(BaseColumns._ID, SQLiteUser.EMAIL, SQLiteUser.SENHA)

        // Filter results WHERE "title" = 'My Title'
        val selection = "${SQLiteUser.EMAIL} = ?"
        val selectionArgs = arrayOf(email)

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${SQLiteUser.EMAIL} DESC"

        val cursor = db.query(
            SQLiteUser.TABLE_USER,   // The table to query
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
                val nome = getString(getColumnIndexOrThrow(SQLiteUser.NOME))
                val senha = getString(getColumnIndexOrThrow(SQLiteUser.SENHA))
                val cpf = getString(getColumnIndexOrThrow(SQLiteUser.CPF))
                val email_sql = getString(getColumnIndexOrThrow(SQLiteUser.EMAIL))
                val cartao_num = getString(getColumnIndexOrThrow(SQLiteUser.CARTAO_NUMERO))
                val cartao_val = getString(getColumnIndexOrThrow(SQLiteUser.CARTAO_VALIDADE))
                val cartao_cod = getString(getColumnIndexOrThrow(SQLiteUser.CARTAO_COD))
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
            put(SQLiteUser.NOME, user.nome)
            put(SQLiteUser.SENHA, user.senha)
            put(SQLiteUser.CARTAO_NUMERO, user.cartao.numero)
            put(SQLiteUser.CARTAO_VALIDADE, user.cartao.validade)
            put(SQLiteUser.CARTAO_COD, user.cartao.codigo)
        }

        // Which row to update, based on the title
        val selection = "${SQLiteUser.EMAIL} LIKE ?"
        val selectionArgs = arrayOf(user.email.endereco)
        val count = db.update(
            SQLiteUser.TABLE_USER,
            values,
            selection,
            selectionArgs)

        return (count > 0)
    }

    override fun getAll(): MutableList<User> {
        val dbHelper = SQLiteUser(context )
        val db = dbHelper.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(BaseColumns._ID, SQLiteUser.EMAIL, SQLiteUser.SENHA, SQLiteUser.NOME, SQLiteUser.CPF, SQLiteUser.CARTAO_VALIDADE, SQLiteUser.CARTAO_NUMERO, SQLiteUser.CARTAO_COD)

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${SQLiteUser.EMAIL} DESC"

        val cursor = db.query(
            SQLiteUser.TABLE_USER,   // The table to query
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
                val nome = getString(getColumnIndexOrThrow(SQLiteUser.NOME))
                val senha = getString(getColumnIndexOrThrow(SQLiteUser.SENHA))
                val cpf = getString(getColumnIndexOrThrow(SQLiteUser.CPF))
                val email_ = getString(getColumnIndexOrThrow(SQLiteUser.EMAIL))
                val cartao_num = getString(getColumnIndexOrThrow(SQLiteUser.CARTAO_NUMERO))
                val cartao_val = getString(getColumnIndexOrThrow(SQLiteUser.CARTAO_VALIDADE))
                val cartao_cod = getString(getColumnIndexOrThrow(SQLiteUser.CARTAO_COD))
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