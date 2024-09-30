package com.bypriyan.ostiamare.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bypriyan.ostiamare.adapter.AdapterPlayer
import com.bypriyan.ostiamare.databinding.ActivityMainBinding
import com.bypriyan.ostiamare.viewModel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addPlayerBtn.setOnClickListener {
            startActivity(Intent(this, AddPlayerActivity::class.java))
        }

        playerViewModel.allPlayers.observe(this, Observer { players ->
            binding.playerRv.adapter = AdapterPlayer(this, players)
        })

        binding.addGameBtn.setOnClickListener{
            startActivity(Intent(this, AddGameActivity::class.java))
        }

        binding.addTrainingBtn.setOnClickListener{
            startActivity(Intent(this, TrainingActivity::class.java))
        }

    }
}