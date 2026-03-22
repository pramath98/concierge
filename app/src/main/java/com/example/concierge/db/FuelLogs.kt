package com.example.concierge.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FuelLogs(
    val fuelPump:String,
    val date: java.util.Date,
    val liters: Double,
     val cost: Double,
    val distanceToDate: Double,
    val vehicleId: Int,
    val userId: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
