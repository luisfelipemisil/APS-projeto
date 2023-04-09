package com.app.myapplication.model.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.app.myapplication.model.entitie.Cliente


class SQLitePedidos(context: Context): SQLiteOpenHelper(context, "nossoPet", null, 2) {

        companion object FeedEntry : BaseColumns {
            val DATABASE_NAME = "nossoPet"
            val TABLE_PEDIDOS = "pedidos"
            val NOME = "NOME"
            val STATUS = "STATUS"
            val IDADE = "IDADE"
            val RACA = "RACA"
            val GATO = "GATO"
            val CACHORRO = "CACHORRO"
            val FILHOTE = "FILHOTE"
            val ENDERECO = "ENDERECO"
            val DESCRICAO = "DESCRICAO"
            val CLIENTE_NOME = "CLIENTE_NOME"
            val CLIENTE_EMAIL = "CLIENTE_EMAIL"
            var DATABASE_VERSION = 1
        }



        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${FeedEntry.TABLE_PEDIDOS} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${FeedEntry.NOME} TEXT," +
                    "${FeedEntry.STATUS} TEXT," +
                    "${FeedEntry.IDADE} INTEGER," +
                    "${FeedEntry.RACA} TEXT," +
                    "${FeedEntry.GATO} TEXT," +
                    "${FeedEntry.CACHORRO} TEXT," +
                    "${FeedEntry.FILHOTE} TEXT," +
                    "${FeedEntry.ENDERECO} TEXT," +
                    "${FeedEntry.DESCRICAO} TEXT," +
                    "${FeedEntry.CLIENTE_NOME} TEXT," +
                    "${FeedEntry.CLIENTE_NOME} TEXT," +
                    "${FeedEntry.CLIENTE_EMAIL} TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedEntry.TABLE_PEDIDOS}"

        override fun onCreate(db: SQLiteDatabase?) {
            if (db != null) {
                db.execSQL(SQL_CREATE_ENTRIES)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            if (db != null) {
                db.execSQL(SQL_DELETE_ENTRIES)
            }
            onCreate(db)
        }
    }