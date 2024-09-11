package com.example.foodeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodeapp.data.repo.FoodERepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(var fRepo: FoodERepository): ViewModel() {

    fun register(full_name: String, email: String, phone: String, password: String){
        CoroutineScope(Dispatchers.Main).launch {
           fRepo.register(full_name,email,phone,password)
        }
    }
}