package com.example.foodeapp.data.repo

import android.content.Context
import android.text.SpannableString
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.R
import com.example.foodeapp.data.datasource.FoodEDataSource
import com.example.foodeapp.data.entity.Basket
import com.example.foodeapp.data.entity.Favs
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users

class FoodERepository {

    var fds = FoodEDataSource()

    suspend fun uploadFoods(): List<Foods> = fds.uploadFoods()

    suspend fun search(searchKeyWord: String): List<Foods> = fds.search(searchKeyWord)

    suspend fun addBasket(yemek_adi: String, yemek_resim_adi:String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String) = fds.addBasket(yemek_adi,yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    suspend fun login(context: Context, fragment: Fragment, email: String, password: String) = fds.login(context, fragment, email, password)

    suspend fun uploadFavs(user: Users): List<Favs> = fds.uploadFavs(user)

    suspend fun uploadBasket(user: Users): List<Basket> = fds.uploadBasket(user)

    suspend fun removeItemBasket(basket_id: Int) = fds.removeItemBasket(basket_id)

    suspend fun register(full_name: String, email: String,phone: String, password: String) = fds.register(full_name, email, phone, password)

    suspend fun removeFav(food_id: Int) = fds.removeFav(food_id)


}