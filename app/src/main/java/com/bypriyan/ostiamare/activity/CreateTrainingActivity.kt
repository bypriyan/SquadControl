package com.bypriyan.ostiamare.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bypriyan.ostiamare.R
import com.bypriyan.ostiamare.adapter.AdapterPlayer
import com.bypriyan.ostiamare.adapter.PlayerAdapter
import com.bypriyan.ostiamare.databinding.ActivityCreateTrainingBinding
import com.bypriyan.ostiamare.databinding.ActivityMainBinding
import com.bypriyan.ostiamare.model.ModelPlayer
import com.bypriyan.ostiamare.model.Training
import com.bypriyan.ostiamare.viewModel.TrainingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateTrainingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateTrainingBinding
    private val viewModel: TrainingViewModel by viewModels()
    private val selectedAbsentPlayers = mutableListOf<Int>() // To store absent player IDs
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView and Adapter
        playerAdapter = PlayerAdapter { player, isChecked ->
            if (isChecked) {
                selectedAbsentPlayers.add(player.id)
            } else {
                selectedAbsentPlayers.remove(player.id)
            }
        }
        binding.playerRv.adapter = playerAdapter

        // Observe players from ViewModel and submit them to the adapter
        viewModel.allPlayers.observe(this) { players ->
            playerAdapter.submitList(players)
        }

        // Set up done button to save the training session
        binding.doneBtn.setOnClickListener {
            val date = binding.date.editText?.text.toString()
            if (date.isNotEmpty()) {
                saveTrainingSession(date)
            } else {
                Toast.makeText(this, "Please enter a date", Toast.LENGTH_SHORT).show()
            }
        }

        // Set back button functionality
        binding.backBtn.setOnClickListener {
            finish()
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

    private fun saveTrainingSession(date: String) {
        val training = Training(date = date, absentPlayers = selectedAbsentPlayers)
        viewModel.insertTraining(training)
        Toast.makeText(this, "Training session saved", Toast.LENGTH_SHORT).show()
        finish() // Go back to the previous screen after saving
    }
}
