package com.simphiwe.footballapp.view.players.playerlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simphiwe.footballapp.R
import com.simphiwe.footballapp.data.model.player.Response
import com.squareup.picasso.Picasso


class PlayersAdapter : RecyclerView.Adapter<PlayersAdapter.MyViewHolder>() {

    var items =  ArrayList<Response>()
    private lateinit var listener: OnItemClickListener

    fun setUpdatedData(items: List<Response>){
        this.items = items as ArrayList<Response>
        notifyDataSetChanged()
    }


    interface OnItemClickListener {
        fun onItemClick(response: Response, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
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
        holder.bind(items.get(position))
        holder.itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(items[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}