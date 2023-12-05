package com.example.tp4.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp4.database.entities.Schedule
import com.example.tp4.database.entities.ScheduleInterface

class BusScheduleViewModel(private val scheduleDao: ScheduleInterface) : ViewModel() {
    class BusScheduleViewModelFactory(private val scheduleDao: ScheduleInterface) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BusScheduleViewModel(scheduleDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
    fun fullSchedule(): List<Schedule> = scheduleDao.getAll()

    fun scheduleByStop(stop: String): List<Schedule> = scheduleDao.getScheduleByStop(stop)

}