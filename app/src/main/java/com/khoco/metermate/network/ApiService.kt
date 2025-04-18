package com.khoco.metermate.network

import com.khoco.metermate.model.PowerRates
import retrofit2.http.GET

interface ApiService {
    @GET("rates") // adjust this if you're hosting under a different route
    suspend fun getPowerRates(): PowerRates
}
