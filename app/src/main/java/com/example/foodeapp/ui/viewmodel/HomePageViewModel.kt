package com.example.foodeapp.ui.viewmodel

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.data.repo.FoodERepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageViewModel: ViewModel() {
    var fRepo = FoodERepository()
    var foodList = MutableLiveData<List<Foods>>()

    init {
        uploadFoods()
    }


    fun uploadFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = fRepo.uploadFoods()
        }
    }

    fun search(searchKeyWord: String){
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = fRepo.search(searchKeyWord)
        }
    }

}