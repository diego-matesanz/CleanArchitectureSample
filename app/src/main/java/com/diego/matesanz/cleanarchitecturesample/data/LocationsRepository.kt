package com.diego.matesanz.cleanarchitecturesample.data

import com.diego.matesanz.cleanarchitecturesample.domain.Location

class LocationsRepository(
    private val locationPersistenceSource: LocationPersistenceSource,
    private val deviceLocationSource: DeviceLocationSource
) {

    suspend fun getSavedLocations(): List<Location> = locationPersistenceSource.getPersistedLocations()

    suspend fun requestNewLocation(): List<Location> {
        val newLocation = deviceLocationSource.getDeviceLocation()
        locationPersistenceSource.saveNewLocation(newLocation)
        return getSavedLocations()
    }
}
