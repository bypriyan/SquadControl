package com.bypriyan.ostiamare.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bypriyan.ostiamare.R
import com.bypriyan.ostiamare.databinding.ActivityCreateTrainingBinding
import com.bypriyan.ostiamare.databinding.ActivityEditPlayerBinding
import com.bypriyan.ostiamare.model.ModelPlayer
import com.bypriyan.ostiamare.viewModel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditPlayerBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    var id:Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra("id")?.let {
            id = it.toInt()
            playerViewModel.getPlayerById(id)

        }

        // Observe the insert status
        playerViewModel.playerInfo.observe(this) { modelPlayer ->
            binding.name.editText?.setText(modelPlayer.name)
            binding.surName.editText?.setText(modelPlayer.surName)
            binding.DOB.editText?.setText(modelPlayer.DOB)
            binding.role.editText?.setText(modelPlayer.Role)
            binding.shirtNumber.editText?.setText(modelPlayer.shirtNo)
            binding.notes.editText?.setText(modelPlayer.notes)
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

        binding.doneBtn.setOnClickListener{
            if(isValid()){

            }
        }

        // Handle the done button click
        binding.doneBtn.setOnClickListener {
            if (isValid()) {
                val updatedPlayer = ModelPlayer(
                    id = id,
                    name = binding.name.editText?.text.toString(),
                    surName = binding.surName.editText?.text.toString(),
                    DOB = binding.DOB.editText?.text.toString(),
                    Role = binding.role.editText?.text.toString(),
                    shirtNo = binding.shirtNumber.editText?.text.toString(),
                    notes = binding.notes.editText?.text.toString()
                )
                playerViewModel.updatePlayer(updatedPlayer)
                finish() // Close activity after updating
            }
        }

    }

    fun isValid(): Boolean {
        val fields = listOf(
            binding.name to "Empty",
            binding.surName to "Empty",
            binding.DOB to "Empty",
            binding.role to "Empty",
            binding.shirtNumber to "Empty",
            binding.notes to "Empty"
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