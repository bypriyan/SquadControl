package com.bypriyan.ostiamare.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bypriyan.ostiamare.R
import com.bypriyan.ostiamare.model.Game
import com.bypriyan.ostiamare.model.ModelPlayer
import com.google.android.material.card.MaterialCardView

class AdapterGame (private val context: Context, private val gameList: List<Game>):
    RecyclerView.Adapter<AdapterGame.HolderGame>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderGame {
        val view = LayoutInflater.from(context).inflate(R.layout.row_players, parent, false)
        return HolderGame(view)    }

    override fun onBindViewHolder(holder: HolderGame, position: Int) {
        val modelGame = gameList[position]

        var date  = modelGame.date
        var opponent = modelGame.opponent
        var result = modelGame.result


        holder.nameTv.text = "$date, opponent : $opponent"
        holder.roleTv.text = "Result : $result"


    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    inner class HolderGame(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView = itemView.findViewById(R.id.nameTv)
        val roleTv: TextView = itemView.findViewById(R.id.roleTv)
    }
}