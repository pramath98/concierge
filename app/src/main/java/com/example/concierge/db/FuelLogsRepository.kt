package com.example.concierge.db

import kotlinx.coroutines.flow.Flow

class FuelLogsRepository(private val fuelLogsDao: FuelLogsDao) {
    fun getFuelLogs(userId: Int): Flow<List<FuelLogs>> = fuelLogsDao.getFuelLogsOrderedByDate(userId)

    suspend fun insert(fuelLog: FuelLogs) {
        fuelLogsDao.upsert(fuelLog)
    }

    suspend fun delete(fuelLog: FuelLogs) {
        fuelLogsDao.delete(fuelLog)
    }
}