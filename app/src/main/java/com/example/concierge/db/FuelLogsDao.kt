package com.example.concierge.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FuelLogsDao {

    @Upsert
    suspend fun upsert(fuelLog: FuelLogs)

    @Delete
    suspend fun delete(fuelLog: FuelLogs)

    @Query("SELECT * FROM FuelLogs WHERE userId = :userId ORDER BY date DESC")
    fun getFuelLogsOrderedByDate(userId: Int): Flow<List<FuelLogs>>

    @Query("SELECT distanceToDate FROM FuelLogs WHERE userId = :userId ORDER BY id DESC LIMIT 1")
    fun getLatestDistance(userId: Int): Flow<Double?>

}
