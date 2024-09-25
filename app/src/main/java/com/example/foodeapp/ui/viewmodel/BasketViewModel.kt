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
    lateinit var user_email: String

    init {
        uploadBasket()
    }


    fun uploadBasket(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                    basketList.value = fRepo.uploadBasket(user_email)

            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun removeItemBasket(basket_id: Int, user_name: String){
        CoroutineScope(Dispatchers.Main).launch {
            fRepo.removeItemBasket(basket_id, user_name)
            if(basketList.value?.size == 1 )
                basketList.value = emptyList()
            uploadBasket()
        }
    }


}