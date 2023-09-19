package com.diego.matesanz.cleanarchitecturesample.app.presentation

import com.diego.matesanz.cleanarchitecturesample.domain.Location

interface Render {

    fun renderLocations(locations: List<Location>)
}
