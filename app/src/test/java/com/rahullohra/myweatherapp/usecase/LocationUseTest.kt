package com.rahullohra.myweatherapp.usecase

import android.location.Address
import android.location.Geocoder
import com.rahullohra.myweatherapp.domain.usecase.LocationUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LocationUseTest {

    val geoCoder: Geocoder = mockk()
    lateinit var locationUseCase: LocationUseCase
    @Before
    fun setup() {
        locationUseCase = LocationUseCase(geoCoder)
    }

    @Test
    fun testProcessAddress() {
        runBlockingTest {

            val latitude: Double = 10.0
            val longitude: Double = 77.7

            val locationList: List<Address> = mockk()
            val address: Address = mockk()

            every {
                geoCoder.getFromLocation(latitude, longitude, 1)
            } returns locationList
            every { locationList.isEmpty() } returns false
            every { locationList[0] } returns address
            every { address.locality } returns "paris"

            val location = locationUseCase.processAddress(latitude, longitude)
            verify { geoCoder.getFromLocation(latitude, longitude, 1) }

        }


    }
}