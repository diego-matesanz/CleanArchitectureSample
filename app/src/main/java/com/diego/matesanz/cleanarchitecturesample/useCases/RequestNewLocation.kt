package com.diego.matesanz.cleanarchitecturesample.useCases

import com.diego.matesanz.cleanarchitecturesample.data.LocationsRepository
import com.diego.matesanz.cleanarchitecturesample.domain.Location

class RequestNewLocation(private val locationsRepository: LocationsRepository) {

    suspend operator fun invoke(): List<Location> = locationsRepository.requestNewLocation()
}