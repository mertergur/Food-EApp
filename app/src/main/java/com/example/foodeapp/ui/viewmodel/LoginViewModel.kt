package com.example.foodeapp.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodeapp.MainActivity
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.data.repo.FoodERepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    var fRepo = FoodERepository()


    fun login(context: Context, fragment: Fragment, email: String, password: String){
        CoroutineScope(Dispatchers.Main).launch {
            fRepo.login(context, fragment, email, password)
        }
    }

}