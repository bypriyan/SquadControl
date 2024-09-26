package com.bypriyan.ostiamare.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bypriyan.ostiamare.R
import com.bypriyan.ostiamare.databinding.ActivityAddPlayerBinding
import com.bypriyan.ostiamare.databinding.ActivityMainBinding

class AddPlayerActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}