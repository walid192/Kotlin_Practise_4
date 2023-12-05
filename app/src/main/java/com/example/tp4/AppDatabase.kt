package com.example.tp4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tp4.database.entities.Schedule
import com.example.tp4.database.entities.ScheduleInterface

@Database(entities = [Schedule::class], version = 1)
abstract class appDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleInterface

    companion object {
        @Volatile
        private var INSTANCE: appDatabase? = null
        fun getDatabase(context: Context): appDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            val instance = Room.databaseBuilder(
                context.applicationContext,
                appDatabase::class.java,
                "app_database"
            ).createFromAsset("database/bus_schedule.db").build()
            INSTANCE = instance
            return instance
        }
    }
}