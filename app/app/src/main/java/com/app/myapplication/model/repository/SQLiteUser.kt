package com.app.myapplication.model.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class SQLiteUser(context: Context): SQLiteOpenHelper(context, "nossoPet", null, 2) {

    companion object FeedEntry : BaseColumns {
        val DATABASE_NAME = "nossoPet"
        val TABLE_USER = "user"
        val NOME = "NOME"
        val SENHA = "SENHA"
        val CPF = "CPF"
        val EMAIL = "EMAIL"
        val CARTAO_NUMERO = "CARTAO_NUMERO"
        val CARTAO_VALIDADE = "CARTAO_VALIDADE"
        val CARTAO_COD = "CARTAO_COD"
        var DATABASE_VERSION = 1
    }

    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${FeedEntry.TABLE_USER} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${FeedEntry.NOME} TEXT," +
                "${FeedEntry.SENHA} TEXT," +
                "${FeedEntry.CPF} TEXT," +
                "${FeedEntry.EMAIL} TEXT," +
                "${FeedEntry.CARTAO_NUMERO} TEXT," +
                "${FeedEntry.CARTAO_VALIDADE} TEXT," +
                "${FeedEntry.CARTAO_COD} TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedEntry.TABLE_USER}"

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