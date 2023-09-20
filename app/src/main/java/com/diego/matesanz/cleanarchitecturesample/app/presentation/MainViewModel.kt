package com.diego.matesanz.cleanarchitecturesample.app.presentation

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

    val stateObservable = Observable<ScreenState<MainState>>()

    fun getSavedLocations() {
        viewModelScope.launch {
            stateObservable.callObservers(ScreenState.Loading)
            val locations = async { getLocations() }
            stateObservable.callObservers(ScreenState.Render(MainState.ShowLocations(locations.await())))
        }
    }

    fun newLocationClicked() {
        viewModelScope.launch {
            stateObservable.callObservers(ScreenState.Loading)
            val locations = async { requestNewLocation() }
            stateObservable.callObservers(ScreenState.Render(MainState.ShowLocations(locations.await())))
            stateObservable.callObservers(ScreenState.Render(MainState.ShowMessage("New location saved")))
        }
    }

    fun onDestroy() {
        stateObservable.clearObservers()
    }
}
