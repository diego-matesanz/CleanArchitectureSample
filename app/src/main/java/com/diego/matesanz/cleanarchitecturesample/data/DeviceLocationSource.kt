package com.diego.matesanz.cleanarchitecturesample.data

import com.diego.matesanz.cleanarchitecturesample.domain.Location

interface DeviceLocationSource {

    fun getDeviceLocation(): Location
}
