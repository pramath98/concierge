package com.example.concierge.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [FuelLogs::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class FuelLogsDatabase: RoomDatabase() {
    abstract val fuelLogsDao: FuelLogsDao

    companion object {
        @Volatile
        private var INSTANCE: FuelLogsDatabase? = null

        fun getDatabase(context: Context): FuelLogsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FuelLogsDatabase::class.java,
                    "fuel_logs_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}