package com.example.tp4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp4.databinding.FragmentBusStopBinding
import com.example.tp4.viewModels.BusScheduleViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BusStopFragment : Fragment() {
    private var _binding: FragmentBusStopBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BusScheduleViewModel by viewModels() {
        BusScheduleViewModel.BusScheduleViewModelFactory(
            (activity?.application as
                    BusScheduleApplication).database.scheduleDao()
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBusStopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        val adapter = BusStopAdapter { schedule ->
            val intent = DetailsActivity.newIntent(this.context, schedule.stop)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        viewModel.fullSchedule().observe(
            viewLifecycleOwner
        ) { schedule ->
            adapter.submitList(schedule)
        }
    }
}
