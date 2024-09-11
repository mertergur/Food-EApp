package com.example.foodeapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodeapp.data.entity.Favs
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

    init {
        uploadFavs()
    }


    fun uploadFavs(){
        CoroutineScope(Dispatchers.Main).launch {
            favList.value = fRepo.uploadFavs(user)
        }
    }
    fun removeFav(food_id: Int){
        CoroutineScope(Dispatchers.Main).launch {
            fRepo.removeFav(food_id)
            uploadFavs()
        }
    }


}