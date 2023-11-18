package com.kush.valorant.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.kush.valorant.R
import com.kush.valorant.data.response.Data

class AgentsAdapter ( val context: Context, val list: List<Data>): Adapter<AgentsAdapter.AgentsViewHolder>()
{
    class AgentsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var image = itemView.findViewById<ImageView>(R.id.imageView)
        var name = itemView.findViewById<TextView>(R.id.name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentsViewHolder {
  val view = LayoutInflater.from(context).inflate(R.layout.agentsdata,parent,false)
        return AgentsViewHolder(view)
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: AgentsViewHolder, position: Int) {
        val agents = list[position]
        holder.name.text = agents.displayName
        Glide.with(context).load(agents.displayIcon).into(holder.image)
    }
}