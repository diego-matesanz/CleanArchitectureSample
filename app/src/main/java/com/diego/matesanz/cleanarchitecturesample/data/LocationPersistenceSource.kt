package com.diego.matesanz.cleanarchitecturesample.data

import com.diego.matesanz.cleanarchitecturesample.domain.Location

interface LocationPersistenceSource {

    fun getPersistedLocations(): List<Location>
    fun saveNewLocation(location: Location)
}
