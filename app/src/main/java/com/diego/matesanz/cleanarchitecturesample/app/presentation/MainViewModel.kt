package com.diego.matesanz.cleanarchitecturesample.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.matesanz.cleanarchitecturesample.useCases.GetLocations
import com.diego.matesanz.cleanarchitecturesample.useCases.RequestNewLocation
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(
    private val getLocations: GetLocations,
    private val requestNewLocation: RequestNewLocation,
    private val render: Render
) : ViewModel() {

    fun getSavedLocations() {
        viewModelScope.launch {
            val locations = async { getLocations() }
            render.renderLocations(locations.await())
        }
    }

    fun newLocationClicked() {
        viewModelScope.launch {
            val locations = async { requestNewLocation() }
            render.renderLocations(locations.await())
        }
    }
}
