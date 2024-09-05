package com.example.foodeapp

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodeapp.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        coloredString()
    }

    fun coloredString(){
        val appName = "FOOD-E"
        val color = ContextCompat.getColor(this,R.color.primary)
        val colorStart = appName.length-2
        val colorEnd = appName.length
        val spannableString = SpannableString(appName)
        spannableString.setSpan(
            ForegroundColorSpan(color),
            colorStart,
            colorEnd,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.appNameTextView.text = spannableString
    }

}