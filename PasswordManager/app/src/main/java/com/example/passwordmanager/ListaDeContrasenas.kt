package com.example.passwordmanager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.BdPassword.Contrasenas
import com.example.passwordmanager.BdPassword.ContraseñasCrud
import com.example.passwordmanager.BdPassword.MyDataClassAdapter
import com.example.passwordmanager.BdPassword.MyDatabaseHelper

class ListaDeContrasenas : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_de_contrasenas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.reciclerview)


        //consulta de las contraseñas
        val myDataClassDao = ContraseñasCrud(MyDatabaseHelper(this))
        val liveData = myDataClassDao.findAll()
        //val contrasenasList : MutableList<List<Contrasenas>> = mutableListOf(liveData)

//      Enviar el historial al reciclerview a traver del adaptador
        recyclerView.layoutManager = LinearLayoutManager(this@ListaDeContrasenas,
            LinearLayoutManager.VERTICAL,false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = MyDataClassAdapter(liveData)

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // No necesitas implementar nada aquí para el desplazamiento
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Elimina el elemento cuando se desliza hacia la izquierda o derecha
                val position = viewHolder.adapterPosition
                // Aquí debes eliminar el elemento de tu lista de datos
            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerView)

    }
}