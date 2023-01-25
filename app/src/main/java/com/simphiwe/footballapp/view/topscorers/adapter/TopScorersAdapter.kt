package com.simphiwe.footballapp.view.topscorers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simphiwe.footballapp.R
import com.simphiwe.footballapp.data.model.topscorers.Response


import com.squareup.picasso.Picasso


class TopScorersAdapter : RecyclerView.Adapter<TopScorersAdapter.MyViewHolder>() {

    var topScorers =  ArrayList<Response>()

    fun setUpdatedData(items: List<Response>){
        this.topScorers = items as ArrayList<Response>
        notifyDataSetChanged()
    }


    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val photo = view.findViewById<ImageView>(R.id.photo)
        val name = view.findViewById<TextView>(R.id.name)
        val nationality = view.findViewById<TextView>(R.id.nationality)

        fun bind(data: Response){
            name.text = data.player.name
            nationality.text = data.player.nationality

            val url = data.player.photo
            Picasso.get().load(url).into(photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(topScorers.get(position))

    }

    override fun getItemCount(): Int {
        return topScorers.size
    }
}