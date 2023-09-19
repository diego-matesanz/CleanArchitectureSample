package com.diego.matesanz.cleanarchitecturesample.app.framework

import com.diego.matesanz.cleanarchitecturesample.data.DeviceLocationSource
import com.diego.matesanz.cleanarchitecturesample.domain.Location
import java.util.Date
import kotlin.random.Random

class FakeLocationSource : DeviceLocationSource {

    private val random = Random(System.currentTimeMillis())

    override fun getDeviceLocation(): Location = Location(
        random.nextFloat() * 180 - 90, random.nextFloat() * 360 - 180, Date()
    )
}
