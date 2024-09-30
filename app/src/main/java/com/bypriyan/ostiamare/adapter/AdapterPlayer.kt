package com.bypriyan.ostiamare.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bypriyan.ostiamare.R
import com.bypriyan.ostiamare.activity.EditPlayerActivity
import com.bypriyan.ostiamare.model.ModelPlayer
import com.google.android.material.card.MaterialCardView

class AdapterPlayer (private val context: Context, private val playerList: List<ModelPlayer>):
    RecyclerView.Adapter<AdapterPlayer.HolderPlayers>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPlayers {
        val view = LayoutInflater.from(context).inflate(R.layout.row_players, parent, false)
        return HolderPlayers(view)    }

    override fun onBindViewHolder(holder: HolderPlayers, position: Int) {
        val modelPlayer = playerList[position]

        var name  = modelPlayer.name
        var surname = modelPlayer.surName
        var role = modelPlayer.Role


        holder.nameTv.text = "$name $surname"
        holder.roleTv.text = "$role"

        holder.itemView.setOnLongClickListener(){
            var intent = Intent(context, EditPlayerActivity::class.java)
            intent.putExtra("id", ""+modelPlayer.id)
            context.startActivity(intent)
            return@setOnLongClickListener false
        }


    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    inner class HolderPlayers(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView = itemView.findViewById(R.id.nameTv)
        val roleTv: TextView = itemView.findViewById(R.id.roleTv)
    }
}