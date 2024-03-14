package com.example.passwordmanager.BdPassword

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "Contrasenas.db", null, 1) {

    //funcion para crear la base de datos con todos sus atributos
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Contrasenas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "image BLOB," +
                "title TEXT," +
                "url TEXT," +
                "username TEXT," +
                "password TEXT," +
                "note TEXT" +
                ");"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Contrasenas")
        onCreate(db)
    }

    fun updateContrasena(contrasena: Contrasenas) {
        val values = ContentValues().apply {
            put("image", contrasena.image)
            put("title", contrasena.title)
            put("url", contrasena.url)
            put("username", contrasena.username)
            put("password", contrasena.password)
            put("note", contrasena.nota)
        }

        val db = writableDatabase
        db.update("Contrasenas", values, "id=?", arrayOf(contrasena.id.toString()))
        db.close()
    }

    fun deleteContrasena(id: Int) {
        val db = writableDatabase
        db.delete("Contrasenas", "id=?", arrayOf(id.toString()))
        db.close()
    }
}