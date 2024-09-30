package com.bypriyan.ostiamare.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bypriyan.ostiamare.R
import com.bypriyan.ostiamare.databinding.ActivityAddGameBinding
import com.bypriyan.ostiamare.databinding.ActivityAddGameDataBinding
import com.bypriyan.ostiamare.model.Game
import com.bypriyan.ostiamare.viewModel.GameViewModel
import com.bypriyan.ostiamare.viewModel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddGameDataActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddGameDataBinding
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGameDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.doneBtn.setOnClickListener{
            if(isValid()){
                gameViewModel.insertGame(Game(id = 0, date = binding.date.editText?.text.toString(),
                    opponent = binding.opponent.editText?.text.toString(),
                    result = binding.result.editText?.text.toString()))
            }
        }

        // Observe the insert status
        gameViewModel.insertStatus.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Game added successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to add game", Toast.LENGTH_SHORT).show()
            }
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


    fun isValid(): Boolean {
        val fields = listOf(
            binding.date to "Empty",
            binding.opponent to "Empty",
            binding.result to "Empty"
        )

        fields.forEach { (field, errorMessage) ->
            if (field.editText?.text?.isEmpty() == true) {
                field.error = errorMessage
                return false
            }
        }
        return true
    }

}