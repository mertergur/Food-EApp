package com.example.foodeapp.ui.viewmodel

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.foodeapp.data.repo.FoodERepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(var fRepo: FoodERepository): ViewModel() {

    fun register(context: Context, fragment: Fragment, email: String, full_name: String, phone: String, password: String){
        CoroutineScope(Dispatchers.Main).launch {
           fRepo.register(context, fragment, email, full_name,phone,password)
        }
    }
}