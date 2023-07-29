package com.example.fakestoreandro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fakestoreandro.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.isVisible = destination.id != R.id.productDetailFragment

            onBottomNavItemReselected(destination)
        }

        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.basket_nav_graph)
        badge.isVisible = true
        badge.number = 4
    }

    private fun onBottomNavItemReselected(
        destination: NavDestination,
    ) {
        binding.bottomNavigation.setOnItemReselectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_nav_graph -> {
                    val startDestinationOfHomeGraph = R.id.homeFragment

                    popIfNotStartDestination(destination.id, startDestinationOfHomeGraph)
                }

                R.id.basket_nav_graph -> {
                    val startDestinationOfHomeGraph = R.id.basketFragment

                    popIfNotStartDestination(destination.id, startDestinationOfHomeGraph)
                }

                R.id.profile_nav_graph -> {
                    val startDestinationOfHomeGraph = R.id.profileFragment

                    popIfNotStartDestination(destination.id, startDestinationOfHomeGraph)
                }
            }
        }
    }

    private fun popIfNotStartDestination(
        destinationId: Int,
        startDestinationOfHomeGraph: Int
    ) {
        if (destinationId != startDestinationOfHomeGraph) {
            navController.popBackStack(destinationId, true)
        }
    }
}