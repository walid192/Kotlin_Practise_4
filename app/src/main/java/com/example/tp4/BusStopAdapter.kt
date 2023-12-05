package com.example.tp4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4.database.entities.Schedule
import androidx.recyclerview.widget.DiffUtil

class BusStopAdapter(private val onItemClick: (Schedule) -> Unit) :
    ListAdapter<Schedule, BusStopAdapter.ViewHolder>(ScheduleDiffCallback()) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val busStopName: TextView
        val busStopTime: TextView

        init {
            busStopName = itemView.findViewById(R.id.busStopName)
            busStopTime = itemView.findViewById(R.id.busStopTime)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bus_stop_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.busStopName.text = item.stop
        holder.busStopTime.text = item.arrival.toString()

        holder.itemView.setOnClickListener {
            onItemClick.invoke(item)
        }
    }



}

class ScheduleDiffCallback: DiffUtil.ItemCallback<Schedule>() {
    override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
        return oldItem == newItem
    }


}


