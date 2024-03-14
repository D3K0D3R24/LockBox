package com.example.passwordmanager.Data.RecyclerViewServices

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.R

class AdapterRecyclerViewServices(val datalist: List<dataClassServices>): RecyclerView.Adapter<AdapterRecyclerViewServices.viewholder>()
{
    class viewholder(itemview: View): RecyclerView.ViewHolder(itemview)
    {
        lateinit var imageView: ImageView
        lateinit var textView: TextView
        init {
            imageView = itemView.findViewById(R.id.imageViewIcon)
            textView = itemView.findViewById(R.id.textViewService)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_service_layout, parent, false)
        return AdapterRecyclerViewServices.viewholder(vista)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.imageView.setImageResource(datalist[position].imgResource)
        holder.textView.text = datalist[position].nameService
    }

}