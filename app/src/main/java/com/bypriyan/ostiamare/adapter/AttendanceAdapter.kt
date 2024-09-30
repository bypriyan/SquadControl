package com.bypriyan.ostiamare.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bypriyan.ostiamare.databinding.RowAttendanceBinding
import com.bypriyan.ostiamare.model.PlayerAttendance

class AttendanceAdapter : ListAdapter<PlayerAttendance, AttendanceAdapter.AttendanceViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val binding = RowAttendanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttendanceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val attendance = getItem(position)
        holder.bind(attendance)
    }

    inner class AttendanceViewHolder(private val binding: RowAttendanceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(attendance: PlayerAttendance) {
            binding.playerNameTv.text = attendance.player.name
            binding.presentCountTv.text = "Present: ${attendance.presentCount}"
            binding.absentCountTv.text = "Absent: ${attendance.absentCount}"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PlayerAttendance>() {
        override fun areItemsTheSame(oldItem: PlayerAttendance, newItem: PlayerAttendance): Boolean =
            oldItem.player.id == newItem.player.id

        override fun areContentsTheSame(oldItem: PlayerAttendance, newItem: PlayerAttendance): Boolean =
            oldItem == newItem
    }
}
