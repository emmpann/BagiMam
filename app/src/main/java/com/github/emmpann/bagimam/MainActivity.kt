package com.github.emmpann.bagimam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.github.emmpann.bagimam.databinding.ActivityMainBinding
import com.github.emmpann.bagimam.home.HomeFragment
import com.github.emmpann.bagimam.login.LoginFragment
import com.github.emmpann.bagimam.login.LoginViewModel
import com.github.emmpann.bagimam.register.RegisterFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, LoginFragment())
//                .commit()
//        }

        navController =
            (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.navView.visibility = View.VISIBLE
                }

                R.id.profileFragment -> {
                    binding.navView.visibility = View.VISIBLE
                }

                R.id.donationFragment -> {
                    binding.navView.visibility = View.VISIBLE
                }

                else -> {
                    binding.navView.visibility = View.GONE
                }
            }
        }
        setStartDestination()
    }

    private fun setStartDestination() {
        viewModel.userFirstTime.observe(this) {
            if (it) {
                navController.navInflater.inflate(R.navigation.main_navigation)
                setupWithNavController(binding.navView, navController)
                Log.d("MainActivity - navigation", "first time: $it")
            } else {
                viewModel.token.observe(this) { token ->
                    Log.d("MainActivity - navigation", "token: $token")
                    navController.navInflater.inflate(R.navigation.main_navigation).apply {
                        setStartDestination(if (token.isNotEmpty()) R.id.homeFragment else R.id.loginFragment)
                        navController.graph = this
                        setupWithNavController(binding.navView, navController)
                    }
                }
            }

//            viewModel.viewModelScope.launch {
//                delay(500)
//                splashScreen.setKeepOnScreenCondition{ false }
//            }
        }
    }
}