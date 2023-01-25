package com.simphiwe.footballapp.view.teams.teamslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simphiwe.footballapp.R
import  com.simphiwe.footballapp.data.model.teams.Response
import com.squareup.picasso.Picasso


class TeamsAdapter() : RecyclerView.Adapter<TeamsAdapter.MyViewHolder>() {

    var teams =  ArrayList<Response>()
    var teamsFiltered = ArrayList<Response>()

    fun setUpdatedData(items: List<Response>){
        this.teamsFiltered = items as ArrayList<Response>
        notifyDataSetChanged()
    }

    fun clear(){
        teamsFiltered.clear();
        notifyDataSetChanged();
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
        holder.bind(teamsFiltered.get(position))
    }

    override fun getItemCount(): Int {
        return teamsFiltered.size
    }
}