package com.example.concierge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.concierge.db.FuelLogs
import com.example.concierge.db.FuelLogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FuelLogsViewModel(private val repository: FuelLogsRepository) : ViewModel() {

    fun getFuelLogs(userId: Int): Flow<List<FuelLogs>> = repository.getFuelLogs(userId)

    fun insert(fuelLog: FuelLogs) = viewModelScope.launch {
        repository.insert(fuelLog)
    }

    fun delete(fuelLog: FuelLogs) = viewModelScope.launch {
        repository.delete(fuelLog)
    }

    fun getLatestDistance(userId: Int): Flow<Double?> {
        return repository.getLatestDistance(userId)
    }
}

class FuelLogsViewModelFactory(private val repository: FuelLogsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FuelLogsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FuelLogsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}