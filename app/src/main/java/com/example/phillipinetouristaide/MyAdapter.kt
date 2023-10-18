package com.example.phillipinetouristaide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private var newsList : ArrayList<Categories>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    var onItemClick: ((Categories) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_listplaces,
            parent,false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    //Binding for ImageView and TextView in activity_listplaces.xml
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.dataImage.setImageResource(currentItem.dataImage)
        holder.dataTitle.text = currentItem.dataTitle

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val dataImage : ImageView = itemView.findViewById(R.id.image)
        val dataTitle : TextView = itemView.findViewById(R.id.title)

    }
}