package com.example.passwordmanager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.BdPassword.ContraseñasCrud
import com.example.passwordmanager.BdPassword.MyDataClassAdapter
import com.example.passwordmanager.BdPassword.MyDatabaseHelper

class Historial_de_Password : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial_de_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.reciclerview)


        //consulta de las contraseñas
        val myDataClassDao = ContraseñasCrud(MyDatabaseHelper(this))
        val liveData = myDataClassDao.findAll()
        //val contrasenasList: MutableList<Contrasenas> = ContraseñasCrud.findAll()


//      Enviar el historial al reciclerview a traver del adaptador
        recyclerView.layoutManager = LinearLayoutManager(this@Historial_de_Password,
            LinearLayoutManager.VERTICAL,false)
        recyclerView.setHasFixedSize(true)
        val adpatador=MyDataClassAdapter(liveData)

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

                // Elimina la contraseña de la base de datos
                val bd = MyDatabaseHelper(this@Historial_de_Password)
                bd.deleteContrasena(liveData[position].id)

                // Elimina el elemento de la lista de datos
                liveData.removeAt(position)
                adpatador.notifyItemRemoved(position)

            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = adpatador


    }
}