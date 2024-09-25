package com.example.foodeapp

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.ActivityAuthBinding
import com.example.foodeapp.ui.fragment.LoginFragment
import com.example.foodeapp.ui.viewmodel.HomePageViewModel
import com.example.foodeapp.ui.viewmodel.LoginViewModel
import com.example.foodeapp.util.coloredString
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    var auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedColor: Int = R.color.primary
        binding.appNameTextView.coloredString(binding.appNameTextView,selectedColor,2,this)
    }
}