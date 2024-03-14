package com.example.passwordmanager.BdPassword

import android.R.attr.label
import android.R.attr.text
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.R

class MyDataClassAdapter(private val datalist: MutableList<Contrasenas>) : RecyclerView.Adapter<MyDataClassAdapter.viewHolderClass>() {

    class viewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var imageView: ImageView
        lateinit var titleTextView: TextView
        lateinit var passwordTextView: TextView

        init {
            imageView = itemView.findViewById(R.id.imageView)
            titleTextView = itemView.findViewById(R.id.titleTextView)
            passwordTextView = itemView.findViewById(R.id.passwordTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderClass {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.diseno_recicleview, parent, false)
        return viewHolderClass(vista)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: viewHolderClass, position: Int) {
        val contrasena = datalist[position]
        holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(contrasena.image, 0, contrasena.image.size))
        holder.titleTextView.text = contrasena.title
        holder.passwordTextView.text = contrasena.password

        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(16, 16, 16, 16)
        holder.itemView.layoutParams = layoutParams

        holder.itemView.setOnClickListener {
            // al darle clic a un elemento
            val context = holder.itemView.context
            val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("contrasena_title", contrasena.password)
            clipboardManager.setPrimaryClip(clip)
            //val myDataClassDao = ContraseñasCrud(MyDatabaseHelper(holder.itemView.context))
            val message = "Contraseña copiado al portapapeles: ${contrasena.password}"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}

