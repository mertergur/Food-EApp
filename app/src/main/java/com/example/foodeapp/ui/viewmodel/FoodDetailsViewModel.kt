package com.example.foodeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodeapp.data.repo.FoodERepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailsViewModel @Inject constructor(var fRepo: FoodERepository): ViewModel() {


    fun addBasket(yemek_adi: String, yemek_resim_adi:String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String){
        CoroutineScope(Dispatchers.Main).launch {
            fRepo.addBasket(yemek_adi, yemek_resim_adi, yemek_fiyat,yemek_siparis_adet, kullanici_adi)
        }
    }
}