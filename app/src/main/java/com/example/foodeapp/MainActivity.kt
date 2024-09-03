package com.example.foodeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodeapp.databinding.ActivityMainBinding
import com.example.foodeapp.ui.fragment.BasketFragment
import com.example.foodeapp.ui.fragment.FavFragment
import com.example.foodeapp.ui.fragment.HomePageFragment
import com.example.foodeapp.ui.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navHostFragment.navController)



/*
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homePageFragment -> replaceFragment(HomePageFragment())
                R.id.basketFragment -> replaceFragment(BasketFragment())
                R.id.favFragment -> replaceFragment(FavFragment())
                R.id.profileFragment -> replaceFragment(ProfileFragment())
                else -> {
                }
            }
            true
        }
        */
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.navHostFragment,fragment)
        fragmentTransaction.commit()
    }
}