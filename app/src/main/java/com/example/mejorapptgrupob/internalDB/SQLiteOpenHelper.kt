package com.example.mejorapptgrupob.internalDB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

private const val SQL_CREATE_ENTRIES1 = "CREATE TABLE IF NOT EXISTS preguntas" +
        "(Id integer PRIMARY KEY, Pregunta text, factor integer)"
private const val SQL_CREATE_ENTRIES2 = "CREATE TABLE IF NOT EXISTS " +
        "respuestas(intento integer PRIMARY KEY AUTOINCREMENT,nick text, " +
        "factor1 integer, factor2 integer,factor3 integer,factor4 integer)"
private const val SQL_DELETE_ENTRIES1 = "DROP TABLE IF EXISTS preguntas"
private const val SQL_DELETE_ENTRIES2 = "DROP TABLE IF EXISTS respuestas"


class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES1)
        db.execSQL(SQL_CREATE_ENTRIES2)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL(SQL_DELETE_ENTRIES1)
        db.execSQL(SQL_DELETE_ENTRIES2)
        onCreate(db)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "p1_preguntas.db"
    }

}