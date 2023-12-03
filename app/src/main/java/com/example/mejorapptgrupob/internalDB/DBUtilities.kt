package com.example.mejorapptgrupob.internalDB

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream


class DBUtilities (private val inputStream: InputStream)  {

    companion object {
        fun inicializar(inputStream: InputStream) {
            val texto = leerArchivo(inputStream)
            val bh = DBHelper(this)
            val db = bh.writableDatabase
            db.beginTransaction()
            for (i in texto.indices) {
                if (texto[i] !== "") {
                    val linea = texto[i].split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val cvalue = ContentValues()
                    cvalue.put("Id", linea[0])
                    cvalue.put("Pregunta", linea[1])
                    cvalue.put("factor", linea[2])
                    db.insert("Preguntas", null, cvalue)
                }
            }
            db.setTransactionSuccessful()
            db.endTransaction()
        }

        private fun leerArchivo(myInputStream: InputStream): Array<String> {
            // Read the contents of the InputStream
            val byteArrayOutputStream = ByteArrayOutputStream()
            try {
                var i = myInputStream.read()
                while (i != -1) {
                    byteArrayOutputStream.write(i)
                    i = myInputStream.read()
                }
                myInputStream.close()
            } catch (io: IOException) {
                io.printStackTrace()
            }
            return byteArrayOutputStream.toString().split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }

        private fun obtenerPregunta(i: Int) {
            val bh = DBHelper(this)
            val dbR: SQLiteDatabase = bh.readableDatabase
            val c = dbR.rawQuery("SELECT Pregunta,factor FROM Preguntas Where Id = $i", null)
            if (c.moveToFirst()) {
                do {
                    val pregunta = c.getString(0)
                    val factor = c.getInt(1)
                } while (c.moveToNext())
            }
            c.close()
            dbR.close()
        }
    }
}