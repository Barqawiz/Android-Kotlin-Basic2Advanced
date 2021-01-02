package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.*
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class SleepNightAdapter : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {
// this deprecated and replaced with list adapter which work with diff function
//class SleepNightAdapter : RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {

    /*
    deprecated, replace the functionality from recycle view adapter to list adapter
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    */
    /*
    override fun getItemCount(): Int = data.size
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepNightAdapter.ViewHolder {
        // how to create views from XML layouts - called when recycle view needs view holder to represent an item
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SleepNightAdapter.ViewHolder, position: Int) {
        // display the data for one list item at the specified position
        // deprecated from recycle adapter
        // var item  = data.get(position)
        // list adapter default get item
        var item = getItem(position)

        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SleepNight) {
            binding.sleepNight = item
            binding.executePendingBindings()
            // deprecated, replaced with binding adapter
            // val res   = itemView.context.resources
            // binding.textSleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            // binding.textQuality.text = convertNumericQualityToString(item.sleepQuality, res)
            // binding.imageQuality.setImageResource(convertNumericQualityToDrawable(item.sleepQuality, res))
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
                // deprecated, none binding code
                /*val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_sleep_night, parent, false)

                return ViewHolder(view)*/
            }
        }
    }


}