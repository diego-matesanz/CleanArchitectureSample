package com.diego.matesanz.cleanarchitecturesample.useCases

import com.diego.matesanz.cleanarchitecturesample.data.LocationsRepository
import com.diego.matesanz.cleanarchitecturesample.domain.Location

class GetLocations(private val locationsRepository: LocationsRepository) {

    operator fun invoke(): List<Location> = locationsRepository.getSavedLocations()
}
