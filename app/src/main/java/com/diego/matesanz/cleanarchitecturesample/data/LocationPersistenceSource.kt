package com.diego.matesanz.cleanarchitecturesample.data

import com.diego.matesanz.cleanarchitecturesample.domain.Location

interface LocationPersistenceSource {

    suspend fun getPersistedLocations(): List<Location>
    fun saveNewLocation(location: Location)
}
