package com.example.foodeapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodeapp.data.entity.Basket
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.data.repo.FoodERepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(var fRepo: FoodERepository): ViewModel() {
    var basketList = MutableLiveData<List<Basket>>()
    lateinit var user: Users

    init {
        uploadBasket()
    }


    fun uploadBasket(){
        CoroutineScope(Dispatchers.Main).launch {
            basketList.value = fRepo.uploadBasket(user)
        }
    }

    fun removeItemBasket(basket_id: Int){
        CoroutineScope(Dispatchers.Main).launch {
            fRepo.removeItemBasket(basket_id)
            uploadBasket()
        }
    }


}