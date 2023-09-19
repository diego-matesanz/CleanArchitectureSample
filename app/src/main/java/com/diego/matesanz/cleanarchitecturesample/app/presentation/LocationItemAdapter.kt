package com.diego.matesanz.cleanarchitecturesample.app.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diego.matesanz.cleanarchitecturesample.R
import com.diego.matesanz.cleanarchitecturesample.databinding.LayoutLocationItemBinding
import com.diego.matesanz.cleanarchitecturesample.domain.Location

class LocationItemAdapter(private var locations: List<Location>) :
    RecyclerView.Adapter<LocationItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_location_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = locations[position]
        holder.apply {
            binding.location = location
        }
    }

    fun setLocations(newList: List<Location>) {
        val locationsDiffCallback = LocationsDiffCallback(locations, newList)
        val diffResult = DiffUtil.calculateDiff(locationsDiffCallback)
        locations = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutLocationItemBinding.bind(view)
    }
}
