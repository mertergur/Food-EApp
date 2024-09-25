package com.example.foodeapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodeapp.data.entity.Favs
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.data.repo.FoodERepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(var fRepo: FoodERepository): ViewModel() {
    var favList = MutableLiveData<List<Favs>>()
    lateinit var user: Users

    fun uploadFavs(){
        viewModelScope.launch {
            favList = fRepo.uploadFavs(user)
        }
    }
    fun removeFav(foodId: Int){
        CoroutineScope(Dispatchers.Main).launch {
            fRepo.removeFav(foodId)
        }
    }

    fun addFav(foodId: Int){
        fRepo.addFav(foodId)
    }
}