package com.diego.matesanz.cleanarchitecturesample.app.presentation

import com.diego.matesanz.cleanarchitecturesample.domain.Location

sealed class MainState {

    class ShowLocations(val locations: List<Location>) : MainState()
    class ShowMessage(val message: String) : MainState()
}
