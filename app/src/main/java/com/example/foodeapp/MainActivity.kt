package com.example.foodeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.ActivityMainBinding
import com.example.foodeapp.ui.fragment.BasketFragment
import com.example.foodeapp.ui.fragment.FavFragment
import com.example.foodeapp.ui.fragment.HomePageFragment
import com.example.foodeapp.ui.fragment.ProfileFragment
import com.example.foodeapp.ui.viewmodel.HomePageViewModel

class MainActivity : AppCompatActivity() {
    private val viewModelHome: HomePageViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navHostFragment.navController)

        val user = intent.getSerializableExtra("user") as Users

        val bundle = Bundle()
        bundle.putSerializable("user", user)

        binding.bottomNavigationView.setOnClickListener {
            when(it.id){
                R.id.homePageFragment -> {
                    navHostFragment.navController.navigate(R.id.homePageFragment, bundle)
                }
                R.id.favFragment -> {
                    navHostFragment.navController.navigate(R.id.favFragment, bundle)
                }
                R.id.basketFragment -> {
                    navHostFragment.navController.navigate(R.id.basketFragment, bundle)
                }
                R.id.profileFragment -> {
                    navHostFragment.navController.navigate(R.id.profileFragment, bundle)
                }
            }
        }

    }

}