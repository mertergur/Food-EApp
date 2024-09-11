package com.example.foodeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodeapp.data.repo.FoodERepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    var fRepo = FoodERepository()

    fun register(full_name: String, email: String, phone: String, password: String){
        CoroutineScope(Dispatchers.Main).launch {
           fRepo.register(full_name,email,phone,password)
        }
    }
}