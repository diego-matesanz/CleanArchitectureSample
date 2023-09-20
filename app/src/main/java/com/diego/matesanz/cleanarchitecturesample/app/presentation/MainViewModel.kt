package com.diego.matesanz.cleanarchitecturesample.app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.matesanz.cleanarchitecturesample.useCases.GetLocations
import com.diego.matesanz.cleanarchitecturesample.useCases.RequestNewLocation
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(
    private val getLocations: GetLocations,
    private val requestNewLocation: RequestNewLocation
) : ViewModel() {

    val mainState: LiveData<ScreenState<MainState>>
        get() = _mainState

    private val _mainState: MutableLiveData<ScreenState<MainState>> = MutableLiveData()

    fun getSavedLocations() {
        viewModelScope.launch {
            _mainState.value = ScreenState.Loading
            val locations = async { getLocations() }
           _mainState.value = ScreenState.Render(MainState.ShowLocations(locations.await()))
        }
    }

    fun newLocationClicked() {
        viewModelScope.launch {
            _mainState.value = ScreenState.Loading
            val locations = async { requestNewLocation() }
            _mainState.value = ScreenState.Render(MainState.ShowLocations(locations.await()))
            _mainState.value = ScreenState.Render(MainState.ShowMessage("New location saved"))
        }
    }
}
