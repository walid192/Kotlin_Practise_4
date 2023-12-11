package com.example.tp4

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4.database.entities.Schedule
import com.example.tp4.databinding.ActivityDetailsBinding
import com.example.tp4.viewModels.BusScheduleViewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var recylerView: RecyclerView
    private lateinit var busStopAdapter: BusStopAdapter

    private val viewModel : BusScheduleViewModel by viewModels() {
        BusScheduleViewModel.BusScheduleViewModelFactory(
            (application as
                    BusScheduleApplication).database.scheduleDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recylerView=binding.recycler
        recylerView.layoutManager= LinearLayoutManager(this)

        busStopAdapter = BusStopAdapter {
            println("Clicked on $it")
        }
        recylerView.adapter = busStopAdapter

        val schedule = intent.getStringExtra("schedule")

        viewModel.scheduleByStop(schedule!!).observe(
            this,
        ) { schedule ->
            busStopAdapter.submitList(schedule)
        }

    }
}