package com.bypriyan.ostiamare.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bypriyan.ostiamare.databinding.RowPlayersBinding
import com.bypriyan.ostiamare.databinding.RowSelectPlayerBinding
import com.bypriyan.ostiamare.model.ModelPlayer

class PlayerAdapter(
    private val onPlayerCheckChanged: (ModelPlayer, Boolean) -> Unit
) : ListAdapter<ModelPlayer, PlayerAdapter.PlayerViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = RowSelectPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = getItem(position)
        holder.bind(player)
    }

    inner class PlayerViewHolder(private val binding: RowSelectPlayerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: ModelPlayer) {
            binding.nameTv.text = player.name
            binding.roleTv.text = player.Role

            binding.checkboxExample.setOnCheckedChangeListener { _, isChecked ->
                onPlayerCheckChanged(player, isChecked)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ModelPlayer>() {
        override fun areItemsTheSame(oldItem: ModelPlayer, newItem: ModelPlayer): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: ModelPlayer, newItem: ModelPlayer): Boolean = oldItem == newItem
    }
}
