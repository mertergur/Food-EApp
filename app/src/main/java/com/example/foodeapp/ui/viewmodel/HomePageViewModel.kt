package com.example.foodeapp.ui.viewmodel

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.data.repo.FoodERepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(var fRepo: FoodERepository): ViewModel() {
    val _foodList = MutableLiveData<List<Foods>>()
    var foodList : LiveData<List<Foods>> = _foodList

    private val originalFoodList = mutableListOf<Foods>()

   /* init {
        viewModelScope.launch {
            originalFoodList.addAll(fRepo.uploadFoods())
            _foodList.value = originalFoodList
        }
    }*/

    fun initVM() {
        if(_foodList.value == null){
            uploadFoods()
        }
    }

    fun uploadFoods(){
        viewModelScope.launch {
            try{
                originalFoodList.addAll(fRepo.uploadFoods())
                _foodList.value = originalFoodList
            }catch (e: Exception){
                Log.e("hata",e.localizedMessage,e)
                println(e)
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            val filteredList = mutableListOf<Foods>()
            if (query.isBlank()) {
                filteredList.addAll(originalFoodList)
            } else {
                for (food in originalFoodList) {
                    if (food.yemek_adi.contains(query, ignoreCase = true)) {
                        filteredList.add(food)
                    }
                }
            }
            _foodList.postValue(filteredList)
        }
    }

    /*fun search(query: String){
        viewModelScope.launch {
            val manipulatedList = mutableListOf<Foods>()
            val currentOriginalList = foodList.value ?: emptyList()

            if(query == ""){
                manipulatedList.addAll(currentOriginalList)
            }else{
                for (item in currentOriginalList){
                    if(item.yemek_adi.contains(query, ignoreCase = true)){
                        manipulatedList.add(item)
                    }
                }
            }
            foodList.postValue(manipulatedList)
        }
    }*/

    /*
    çalışıyor.
    fun search(query: String){
        viewModelScope.launch {
            val filteredList = if (query.isBlank()){
                originalFoodList
            }else{
                originalFoodList.filter { food -> food.yemek_adi.contains(query, ignoreCase = true) }
            }
            _foodList.postValue(filteredList)
        }
    }*/
}