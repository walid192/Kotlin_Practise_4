package com.example.tp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp4.database.entities.Schedule
import com.example.tp4.databinding.ActivityMainBinding
import com.example.tp4.viewModels.BusScheduleViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel : BusScheduleViewModel by viewModels() {
        BusScheduleViewModel.BusScheduleViewModelFactory(
            (application as
                    BusScheduleApplication).database.scheduleDao())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView= binding.recyclerView
        recyclerView.layoutManager= LinearLayoutManager(this)

        val adapter= BusStopAdapter{schedule->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("schedule", schedule.stop)
            startActivity(intent)
        }
        recyclerView.adapter= adapter

        var list = listOf<Schedule>()
        Thread {
            list = viewModel.fullSchedule()
            Handler(Looper.getMainLooper()).post {
                adapter.submitList(list)
            }
        }.start()
    }
}