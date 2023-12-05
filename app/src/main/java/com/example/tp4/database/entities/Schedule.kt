package com.example.tp4.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Schedule(
    @PrimaryKey val id:Int,
    @ColumnInfo(name = "stop_name") val stop: String,
    @ColumnInfo(name = "arrival_time") val arrival: Int,
)
