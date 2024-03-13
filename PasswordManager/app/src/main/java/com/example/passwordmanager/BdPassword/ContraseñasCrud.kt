package com.example.passwordmanager.BdPassword

import android.content.ContentValues
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
class ContraseñasCrud(private val database: MyDatabaseHelper) {

    //funcion para insrtar una contraseña a la base de datos
    fun insertOne(data: Contrasenas): LiveData<Contrasenas> {
        val liveData = MutableLiveData<Contrasenas>()
        val contentValues = ContentValues().apply {
            put("image", data.image)
            put("title", data.title)
            put("url", data.url)
            put("username", data.username)
            put("password", data.password)
            put("note", data.nota)
        }
        val id = database.writableDatabase.insert("Contrasenas", null, contentValues)
        data.id = id.toInt()
        liveData.value = data
        return liveData
    }

    //funcion para obtener todas las contraseñas
    fun findAll(): List<Contrasenas> {
        val dataList = mutableListOf<Contrasenas>()
        val cursor = database.readableDatabase.query("Contrasenas", null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val image = cursor.getBlob(1)
            val title = cursor.getString(2)
            val url = cursor.getString(3)
            val username = cursor.getString(4)
            val password = cursor.getString(5)
            val note = cursor.getString(6)
            dataList.add(Contrasenas(id, image, title, url, username, password, note))
        }
        cursor.close()
        return dataList
    }



//    fun findOne(filter: suspend (Contrasenas) -> Boolean): Contrasenas? {
//        val dataList = findAll { filter(it) }
//        return if (dataList.isNotEmpty()) dataList[0] else null
//    }
//
//    fun updateOne(filter: suspend (Contrasenas) -> Boolean, update: suspend (Contrasenas) -> Contrasenas): Int {
//        val contentValues = ContentValues().apply {
//            update(update(Contrasenas(0, ByteArray(0), "", "", "", "", "")))
//        }
//        return database.writableDatabase.update("Contrasenas", contentValues, "id = ?", arrayOf(filter(Contrasenas(0, ByteArray(0), "", "", "", "", "")).id.toString()))
//    }
//
//    fun deleteOne(filter: suspend (Contrasenas) -> Boolean): Int {
//        return database.writableDatabase.delete("Contrasenas", "id = ?", arrayOf(filter(Contrasenas(0, ByteArray(0), "", "", "", "", "")).id.toString()))
//    }
}