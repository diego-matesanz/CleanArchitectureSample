package com.diego.matesanz.cleanarchitecturesample.app.framework

import com.diego.matesanz.cleanarchitecturesample.data.LocationPersistenceSource
import com.diego.matesanz.cleanarchitecturesample.domain.Location

class InMemoryLocationsPersistenceSource: LocationPersistenceSource {

    private var locations: List<Location> = emptyList()

    override fun getPersistedLocations(): List<Location> = locations

    override fun saveNewLocation(location: Location) {
        locations += location
    }
}
