package com.deevvdd.tmdb

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.deevvdd.tmdb.databinding.ActivityMainBinding
import com.deevvdd.tmdb.utils.hide
import com.deevvdd.tmdb.utils.show
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        setSupportActionBar(binding.toolbar)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.movieDetailFragment -> {
                    binding.ivBack.show()
                }
                else -> binding.ivBack.hide()
            }
        }
        binding.ivBack.setOnClickListener {
            navController.popBackStack()
        }
    }
}
