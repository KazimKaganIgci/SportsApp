package com.kazim.betteryou.ActivityPages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.kazim.betteryou.R
import com.kazim.betteryou.databinding.ActivitySecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //connecting navigation buttons
        val bottomNavigation =binding.bottomNavigationView
        val findNavigation = Navigation.findNavController(this, R.id.hostFragment)
        NavigationUI.setupWithNavController(bottomNavigation,findNavigation)
    }
}