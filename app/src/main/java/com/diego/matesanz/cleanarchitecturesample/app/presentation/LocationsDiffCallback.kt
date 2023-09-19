package com.diego.matesanz.cleanarchitecturesample.app.presentation

import androidx.recyclerview.widget.DiffUtil
import com.diego.matesanz.cleanarchitecturesample.domain.Location

class LocationsDiffCallback(
    private val oldLocationList: List<Location>,
    private val newLocationList: List<Location>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldLocationList.size

    override fun getNewListSize(): Int = newLocationList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldLocationList[oldItemPosition] == newLocationList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldLocationList[oldItemPosition] == newLocationList[newItemPosition]
    }
}
