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
import com.bypriyan.ostiamare.databinding.ActivityAddPlayerBinding
import com.bypriyan.ostiamare.databinding.ActivityMainBinding
import com.bypriyan.ostiamare.model.ModelPlayer
import com.bypriyan.ostiamare.viewModel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPlayerActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddPlayerBinding
    private val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.doneBtn.setOnClickListener{
            if(isValid()){
                playerViewModel.insertPlayer(ModelPlayer(
                    id=0,
                    name = binding.name.editText?.text.toString(),
                    surName = binding.surName.editText?.text.toString(),
                    DOB = binding.DOB.editText?.text.toString(),
                    Role = binding.role.editText?.text.toString(),
                    shirtNo = binding.shirtNumber.editText?.text.toString(),
                    notes = binding.notes.editText?.text.toString()
                ))

            }
        }

        // Observe the insert status
        playerViewModel.insertStatus.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Player added successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to add player", Toast.LENGTH_SHORT).show()
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