package com.bypriyan.ostiamare.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bypriyan.ostiamare.adapter.AdapterGame
import com.bypriyan.ostiamare.adapter.AttendanceAdapter
import com.bypriyan.ostiamare.databinding.ActivityTrainingBinding
import com.bypriyan.ostiamare.viewModel.GameViewModel
import com.bypriyan.ostiamare.viewModel.TrainingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainingBinding
    private val viewModel: TrainingViewModel by viewModels()
    private lateinit var attendanceAdapter: AttendanceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addTrainingBtn.setOnClickListener{
            startActivity(Intent(this, CreateTrainingActivity::class.java))
        }

        // Initialize the adapter and RecyclerView
        attendanceAdapter = AttendanceAdapter()
        binding.gameRv.adapter = attendanceAdapter

        // Observe attendance stats and submit them to the adapter
        viewModel.attendanceStats.observe(this) { stats ->
            attendanceAdapter.submitList(stats)
        }

        binding.backBtn.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        //back pressed
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }
}
