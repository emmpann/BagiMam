package com.github.emmpann.bagimam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.emmpann.bagimam.databinding.ActivityMainBinding
import com.github.emmpann.bagimam.home.HomeFragment
import com.github.emmpann.bagimam.register.RegisterFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, RegisterFragment())
                .commit()
        }
    }
}