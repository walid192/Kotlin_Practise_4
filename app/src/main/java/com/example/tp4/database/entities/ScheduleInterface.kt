package com.example.tp4.database.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ScheduleInterface {
    @Query("SELECT * FROM Schedule order by arrival_time ASC")
    fun getAll(): LiveData<List<Schedule>>

    @Query("SELECT * FROM Schedule WHERE stop_name LIKE :stop")
    fun getScheduleByStop(stop: String): LiveData<List<Schedule>>

}

