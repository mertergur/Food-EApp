package com.example.foodeapp.data.datasource

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.MainActivity
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Basket
import com.example.foodeapp.data.entity.Favs
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodEDataSource {

    suspend fun uploadFoods(): List<Foods> =
        withContext(Dispatchers.IO){
            val foodList = ArrayList<Foods>()
            val f1 = Foods(1, "Ayran", "ayran", 25)
            val f2 = Foods(2, "Baklava", "baklava", 85)
            val f3 = Foods(3, "Fanta", "fanta", 25)
            val f4 = Foods(4, "Izgara Somon", "izgarasomon", 200)
            val f5 = Foods(5, "Izgara Tavuk", "izgaratavuk", 170)
            val f6 = Foods(6, "Kadayıf", "kadayif", 75)
            val f7 = Foods(7, "Kahve", "kahve", 70)
            val f8 = Foods(8, "Köfte", "kofte", 150)
            val f9 = Foods(9, "Lazanya", "lazanya", 135)
            val f10 = Foods(10, "Makarna", "makarna", 110)
            val f11 = Foods(11, "Pizza", "pizza", 130)
            val f12 = Foods(12, "Su", "su", 15)
            val f13 = Foods(13, "Sütlaç", "sutlac", 75)
            val f14 = Foods(14, "Tiramisu", "tiramisu", 75)


            foodList.add(f1)
            foodList.add(f2)
            foodList.add(f3)
            foodList.add(f4)
            foodList.add(f5)
            foodList.add(f6)
            foodList.add(f7)
            foodList.add(f8)
            foodList.add(f9)
            foodList.add(f10)
            foodList.add(f11)
            foodList.add(f12)
            foodList.add(f13)
            foodList.add(f14)

            return@withContext foodList
        }

    suspend fun search(searchKeyWord: String): List<Foods> =
        withContext(Dispatchers.IO){
            val foodList = ArrayList<Foods>()
            val f1 = Foods(1, "Ayran", "ayran", 25)
            foodList.add(f1)

            return@withContext foodList
        }

    suspend fun addBasket(yemek_adi: String, yemek_resim_adi:String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String){

        Log.e("addBasket","$yemek_siparis_adet adet $yemek_adi $yemek_fiyat karşılığında $kullanici_adi adlı kullanıcının sepetine eklendi. Yemek resmi: $yemek_resim_adi")
    }

    suspend fun login(context: Context, fragment: Fragment, email: String, password: String) {
        val userList = ArrayList<Users>()


        val user1 = Users(1,"mert ergür","mert2626@live.com","05369548515","123123")
        val user2 = Users(2,"sude sünör","sude2626@live.com","05331112222","321321")
        userList.add(user1)
        userList.add(user2)

        var user = Users(0,"","", "","")
        userList.forEach {
            if (it.user_email == email) {
                user = it
            }
        }

        if(email == user.user_email && password == user.user_password){
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("user", user)
            context.startActivity(intent)
            fragment.requireActivity().finish()
        }else{
            Log.e("giriş","hatalı giriş")
        }
    }

    suspend fun uploadFavs(user: Users): List<Favs> =
        withContext(Dispatchers.IO) {
            val favList = ArrayList<Favs>()
            val userFavList = ArrayList<Favs>()

            val fav1 = Favs(1, "Baklava", "baklava", 85, 1)
            val fav2 = Favs(2, "Fanta", "fanta", 25, 1)
            val fav3 = Favs(3, "Kahve", "kahve", 70, 1)
            val fav4 = Favs(4, "Köfte", "kofte", 150, 1)
            val fav5 = Favs(5, "Lazanya", "lazanya", 135, 1)
            val fav6 = Favs(6, "Makarna", "makarna", 110, 2)
            val fav7 = Favs(7, "Sütlaç", "sutlac", 75, 2)

            favList.add(fav1)
            favList.add(fav2)
            favList.add(fav3)
            favList.add(fav4)
            favList.add(fav5)
            favList.add(fav6)
            favList.add(fav7)

                favList.forEach {
                    if (it.fav_kullanici_id == user.user_id) {
                        userFavList.add(it)
                    }
                }



            return@withContext userFavList
        }

    suspend fun uploadBasket(user: Users): List<Basket> =
        withContext(Dispatchers.IO){
            val basketList = ArrayList<Basket>()
            val userBasketList = ArrayList<Basket>()

            val b1 = Basket(1,"Ayran","ayran",25,10,"mert2626@live.com")
            val b2 = Basket(2,"Baklava","baklava",85,1,"mert2626@live.com")
            val b3 = Basket(3,"Fanta","fanta",25,1,"mert2626@live.com")
            val b4 = Basket(3,"Pizza","pizza",130,3,"sude2626@live.com")
            val b5 = Basket(3,"Makarna","makarna",110,1,"sude2626@live.com")
            val b6 = Basket(3,"Lazanya","lazanya",135,2,"sude2626@live.com")


            val f9 = Foods(9, "Lazanya", "lazanya", 135)
            val f10 = Foods(10, "Makarna", "makarna", 110)
            val f11 = Foods(11, "Pizza", "pizza", 130)

            basketList.add(b1)
            basketList.add(b2)
            basketList.add(b3)
            basketList.add(b4)
            basketList.add(b5)
            basketList.add(b6)

            basketList.forEach {
                if(it.kullanici_adi == user.user_email){
                    userBasketList.add(it)
                }else{
                    println("bune")
                }
            }

            return@withContext userBasketList
        }

    suspend fun removeItemBasket(basket_id: Int){
        Log.e("Basket Item Remove", "$basket_id removed from basket")
    }

    suspend fun register(full_name: String, email: String,phone: String, password: String){
        var user = Users(1,full_name,email,phone,password)
    }

    suspend fun removeFav(food_id: Int){
        Log.e("Fav Item Remove", "$food_id removed from favorites")
    }

}