package com.example.concierge

import android.app.Application
import com.example.concierge.db.FuelLogsDatabase
import com.example.concierge.db.FuelLogsRepository

class ConciergeApplication : Application() {
    val database by lazy { FuelLogsDatabase.getDatabase(this) }
    val repository by lazy { FuelLogsRepository(database.fuelLogsDao) }
}