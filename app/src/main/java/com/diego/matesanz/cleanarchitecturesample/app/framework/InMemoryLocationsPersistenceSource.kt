package com.diego.matesanz.cleanarchitecturesample.app.framework

import com.diego.matesanz.cleanarchitecturesample.data.LocationPersistenceSource
import com.diego.matesanz.cleanarchitecturesample.domain.Location
import kotlinx.coroutines.delay

class InMemoryLocationsPersistenceSource: LocationPersistenceSource {

    private var locations: List<Location> = emptyList()

    override suspend fun getPersistedLocations(): List<Location> {
        delay(2000)
        return locations
    }

    override fun saveNewLocation(location: Location) {
        locations += location
    }
}
