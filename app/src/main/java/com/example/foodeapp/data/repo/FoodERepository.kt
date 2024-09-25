package com.example.foodeapp.data.repo

import android.content.Context
import android.text.SpannableString
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.R
import com.example.foodeapp.data.datasource.FoodEDataSource
import com.example.foodeapp.data.entity.Basket
import com.example.foodeapp.data.entity.Favs
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users

class FoodERepository (var fds: FoodEDataSource) {

    suspend fun uploadFoods(): List<Foods> = fds.uploadFoods()

    suspend fun addBasket(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) = fds.addBasket(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

     fun login(context: Context, fragment: Fragment) = fds.login(context, fragment)

    suspend fun uploadFavs(user: Users): MutableLiveData<List<Favs>> = fds.uploadFavs(user)

    suspend fun uploadBasket(user_email: String): List<Basket> = fds.uploadBasket(user_email)

    suspend fun removeItemBasket(basket_id: Int, user_name: String) = fds.removeItemBasket(basket_id, user_name)

    fun register(context: Context, fragment: Fragment, email: String, full_name: String, phone: String, password: String) = fds.register(context, fragment, email, full_name, phone, password)

    fun removeFav(foodId: Int) = fds.removeFav(foodId)



    fun addFav(foodId: Int) = fds.addFav(foodId)



}