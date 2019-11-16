package com.rahullohra.myweatherapp.domain.usecase

import android.location.Geocoder
import javax.inject.Inject

class LocationUseCase @Inject constructor(val geoCoder: Geocoder) {

    suspend fun processAddress(latitude: Double, longitude: Double): String {
        val list = geoCoder.getFromLocation(latitude, longitude, 1)
        if (list != null && list.isNotEmpty()) {
            return list[0].locality
        } else
            return DEFAULT_LOCATION
    }


    companion object {
        const val DEFAULT_LOCATION = "PARIS"
    }
}