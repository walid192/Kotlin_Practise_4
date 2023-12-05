package com.example.tp4

import android.app.Application

class BusScheduleApplication:Application() {
    val database: appDatabase by lazy {
        appDatabase.getDatabase(this)}
    }