package com.khoco.metermate.model

data class PowerRates(
    val onGridRate: Double,
    val offGridRate: Double,
    val fixedCharge: Double,
    val lastUpdated: String
)
