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
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.R

//
//interface ItemTouchHelperAdapter {
//    fun onItemDismiss(position: Int)
//}
class MyDataClassAdapter(val datalist: List<Contrasenas>) :RecyclerView.Adapter<MyDataClassAdapter.viewHolderClass>(){

    class viewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit var imageView : ImageView
        lateinit var titleTextView : TextView
        lateinit var passwordTextView : TextView
        init {
            imageView = itemView.findViewById(R.id.imageView)
            titleTextView = itemView.findViewById(R.id.titleTextView)
            passwordTextView = itemView.findViewById(R.id.passwordTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderClass {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.diseno_recicleview,parent,false)
        return viewHolderClass(vista)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: viewHolderClass, position: Int) {
        holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(datalist[position].image, 0, datalist[position].image.size))
        holder.titleTextView.text = datalist[position].title
        holder.passwordTextView.text = datalist[position].password

        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(16, 16, 16, 16)
        holder.itemView.layoutParams = layoutParams

        holder.itemView.setOnClickListener {
            //al darle click a un item
        }
    }


//    fun onItemDismiss(position: Int) {
//        //datalist.removeAt(position)
//        notifyItemRemoved(position)
//    }

}
