package com.app.myapplication.model.repository

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.app.myapplication.model.entitie.*

class IReposirotioPedidosSQLite() :RepositorioPedidos{
    lateinit var context: Context
    private lateinit var dbHelper : SQLitePedidos

    constructor(context: Context) : this() {

        this.context = context

    }

    override fun setup(): Boolean {
        return true
    }

    override fun addPedido(ficha:Animal): Boolean {
        // Gets the data repository in write mode
        val dbHelper = SQLitePedidos(context)
        val db = dbHelper.writableDatabase
        if(db != null){
            val values = ContentValues().apply {
                put(SQLitePedidos.NOME, ficha.nome)
                put(SQLitePedidos.RACA, ficha.raca)
                put(SQLitePedidos.GATO, ficha.gato.toString())
                put(SQLitePedidos.CACHORRO, ficha.cachorro.toString())
                put(SQLitePedidos.FILHOTE, ficha.filhote.toString())
                put(SQLitePedidos.STATUS, ficha.status)
                put(SQLitePedidos.IDADE, ficha.idade)
                put(SQLitePedidos.DESCRICAO, ficha.descricao)
                put(SQLitePedidos.ENDERECO, ficha.endereco)
                put(SQLitePedidos.CLIENTE_EMAIL, ficha.cliente.email.endereco)
                put(SQLitePedidos.CLIENTE_NOME, ficha.cliente.nome)


            }
            // Insert the new row, returning the primary key value of the new row
            val newRowId = db.insert(SQLitePedidos.TABLE_PEDIDOS, null, values)
            return (newRowId > 0)
        }
        return false

    }

    override fun removerPedido(nome: String): Boolean {
        val db = dbHelper.writableDatabase
        // Define 'where' part of query.
        val selection = "${SQLitePedidos.NOME} LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(nome)
        // Issue SQL statement.
        val deletedRows = db.delete(SQLitePedidos.TABLE_PEDIDOS, selection, selectionArgs)

        return( deletedRows > 0)
    }

    override fun findPedido(nome: String): Pair<Boolean, Animal> {
        val dbHelper = SQLitePedidos(context )
        val db = dbHelper.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(BaseColumns._ID, SQLitePedidos.NOME, SQLitePedidos.STATUS, SQLitePedidos.CLIENTE_NOME, SQLitePedidos.CLIENTE_EMAIL, SQLitePedidos.CLIENTE_NOME, SQLitePedidos.DESCRICAO, SQLitePedidos.ENDERECO, SQLitePedidos.FILHOTE, SQLitePedidos.GATO, SQLitePedidos.CACHORRO, SQLitePedidos.RACA,SQLitePedidos.IDADE)

        // Filter results WHERE "title" = 'My Title'
        val selection = "${SQLitePedidos.NOME} = ?"
        val selectionArgs = arrayOf(nome)

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${SQLitePedidos.NOME} DESC"

        val cursor = db.query(
            SQLitePedidos.TABLE_PEDIDOS,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val fichas = mutableListOf<Animal>()
        with(cursor) {
            while (moveToNext()) {
                val nome_ = getString(getColumnIndexOrThrow(SQLitePedidos.NOME))
                val status = getString(getColumnIndexOrThrow(SQLitePedidos.STATUS))
                val idade = getInt(getColumnIndexOrThrow(SQLitePedidos.IDADE))
                val raca = getString(getColumnIndexOrThrow(SQLitePedidos.RACA))
                val gato = (getString(getColumnIndexOrThrow(SQLitePedidos.GATO))).toBooleanStrictOrNull()
                val cachorro = (getString(getColumnIndexOrThrow(SQLitePedidos.CACHORRO))).toBooleanStrictOrNull()
                val filhote = (getString(getColumnIndexOrThrow(SQLitePedidos.FILHOTE))).toBooleanStrictOrNull()
                val endereco = getString(getColumnIndexOrThrow(SQLitePedidos.ENDERECO))
                val descricao = getString(getColumnIndexOrThrow(SQLitePedidos.DESCRICAO))
                val cliente_nome = getString(getColumnIndexOrThrow(SQLitePedidos.CLIENTE_NOME))
                val cliente_email = getString(getColumnIndexOrThrow(SQLitePedidos.CLIENTE_EMAIL))
                if(nome_ != null && status != null && raca != null && gato != null && cachorro != null && filhote != null && endereco != null && descricao != null && cliente_nome != null && cliente_email != null){
                    val ficha = Animal(nome_, status, idade, raca, gato, cachorro , filhote, endereco , descricao, Cliente(cliente_nome, Email(cliente_email)) )
                    fichas.add(ficha)
                }
            }
        }
        cursor.close()
        if(fichas.isNotEmpty()){
            return Pair(true, fichas.first() )
        }
        return Pair(false, Animal() )
    }

    override fun editPedido(ficha:Animal): Boolean {
        val db = dbHelper.writableDatabase

        // New value for one column
        val values = ContentValues().apply {
            put(SQLitePedidos.NOME, ficha.nome)
            put(SQLitePedidos.RACA, ficha.raca)
            put(SQLitePedidos.GATO, ficha.gato.toString())
            put(SQLitePedidos.CACHORRO, ficha.cachorro.toString())
            put(SQLitePedidos.FILHOTE, ficha.filhote.toString())
            put(SQLitePedidos.STATUS, ficha.status)
            put(SQLitePedidos.IDADE, ficha.idade)
            put(SQLitePedidos.DESCRICAO, ficha.descricao)
            put(SQLitePedidos.ENDERECO, ficha.endereco)
            put(SQLitePedidos.CLIENTE_EMAIL, ficha.cliente.email.endereco)
            put(SQLitePedidos.CLIENTE_NOME, ficha.cliente.nome)
        }

        // Which row to update, based on the title
        val selection = "${SQLitePedidos.NOME} LIKE ?"
        val selectionArgs = arrayOf(ficha.nome)
        val count = db.update(
            SQLitePedidos.TABLE_PEDIDOS,
            values,
            selection,
            selectionArgs)

        return (count > 0)
    }

    override fun getAll(): MutableList<Animal> {
        val dbHelper = SQLitePedidos(context )
        val db = dbHelper.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(BaseColumns._ID, SQLitePedidos.NOME, SQLitePedidos.STATUS, SQLitePedidos.CLIENTE_NOME, SQLitePedidos.CLIENTE_EMAIL, SQLitePedidos.CLIENTE_NOME, SQLitePedidos.DESCRICAO, SQLitePedidos.ENDERECO, SQLitePedidos.FILHOTE, SQLitePedidos.GATO, SQLitePedidos.CACHORRO, SQLitePedidos.RACA,SQLitePedidos.IDADE)

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${SQLitePedidos.NOME} DESC"

        val cursor = db.query(
            SQLitePedidos.TABLE_PEDIDOS,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val fichas = mutableListOf<Animal>()
        with(cursor) {
            while (moveToNext()) {
                val nome = getString(getColumnIndexOrThrow(SQLitePedidos.NOME))
                val status = getString(getColumnIndexOrThrow(SQLitePedidos.STATUS))
                val idade = getInt(getColumnIndexOrThrow(SQLitePedidos.IDADE))
                val raca = getString(getColumnIndexOrThrow(SQLitePedidos.RACA))
                val gato = (getString(getColumnIndexOrThrow(SQLitePedidos.GATO))).toBooleanStrictOrNull()
                val cachorro = (getString(getColumnIndexOrThrow(SQLitePedidos.CACHORRO))).toBooleanStrictOrNull()
                val filhote = (getString(getColumnIndexOrThrow(SQLitePedidos.FILHOTE))).toBooleanStrictOrNull()
                val endereco = getString(getColumnIndexOrThrow(SQLitePedidos.ENDERECO))
                val descricao = getString(getColumnIndexOrThrow(SQLitePedidos.DESCRICAO))
                val cliente_nome = getString(getColumnIndexOrThrow(SQLitePedidos.CLIENTE_NOME))
                val cliente_email = getString(getColumnIndexOrThrow(SQLitePedidos.CLIENTE_EMAIL))
                if(status != null && raca != null && gato != null && cachorro != null && filhote != null && endereco != null && descricao != null && cliente_nome != null && cliente_email != null){
                    val ficha = Animal(nome, status, idade, raca, gato, cachorro , filhote, endereco , descricao, Cliente(cliente_nome, Email(cliente_email)) )
                    fichas.add(ficha)
                }
            }
        }
        cursor.close()
        return fichas

    }
}