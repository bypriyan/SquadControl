package com.bypriyan.ostiamare.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.bypriyan.ostiamare.R
import com.bypriyan.ostiamare.adapter.AdapterGame
import com.bypriyan.ostiamare.adapter.AdapterPlayer
import com.bypriyan.ostiamare.databinding.ActivityAddGameBinding
import com.bypriyan.ostiamare.databinding.ActivityMainBinding
import com.bypriyan.ostiamare.viewModel.GameViewModel
import com.bypriyan.ostiamare.viewModel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class AddGameActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddGameBinding
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addGameBtn.setOnClickListener{
            startActivity(Intent(this, AddGameDataActivity::class.java))
        }

        gameViewModel.allGames.observe(this, Observer { games ->
            binding.gameRv.adapter = AdapterGame(this, games)
        })

        binding.backBtn.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        //this is change
        //back pressed
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })


    }
}