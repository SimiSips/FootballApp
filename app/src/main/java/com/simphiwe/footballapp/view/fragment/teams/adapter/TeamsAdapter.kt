package com.simphiwe.footballapp.view.fragment.teams.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simphiwe.footballapp.R
import  com.simphiwe.footballapp.view.fragment.teams.teamslist.model.Response
import com.squareup.picasso.Picasso


class TeamsAdapter : RecyclerView.Adapter<TeamsAdapter.MyViewHolder>() {

    var teams =  ArrayList<Response>()

    fun setUpdatedData(items: List<Response>){
        this.teams = items as ArrayList<Response>
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val flag = view.findViewById<ImageView>(R.id.flag)
        val country = view.findViewById<TextView>(R.id.country)
        val code = view.findViewById<TextView>(R.id.code)

        fun bind(data: Response){
            country.text = data.team.name
            code.text = data.team.country

            val url = data.team.logo
            Picasso.get().load(url).into(flag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.teams_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(teams.get(position))
    }

    override fun getItemCount(): Int {
        return teams.size
    }
}