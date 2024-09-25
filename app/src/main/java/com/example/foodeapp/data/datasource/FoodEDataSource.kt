package com.example.foodeapp.data.datasource

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.MainActivity
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Address
import com.example.foodeapp.data.entity.Basket
import com.example.foodeapp.data.entity.Cards
import com.example.foodeapp.data.entity.Favs
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.retrofit.FoodsDao
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodEDataSource(var fdao: FoodsDao, var collectionUser: CollectionReference) {

    var auth: FirebaseAuth = Firebase.auth
    var favList = MutableLiveData<List<Favs>>()

    suspend fun uploadFoods(): List<Foods> =
            fdao.loadFoods().yemekler ?: listOf()


    suspend fun addBasket(yemek_adi: String, yemek_resim_adi:String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String){
        fdao.addFoodToBasket(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

     fun login(context: Context, fragment: Fragment) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userDoc = collectionUser.document(currentUser!!.uid)
        userDoc.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val userAddress = ArrayList<Address>()
                val userCard = ArrayList<Cards>()
                val addressesMap = documentSnapshot.get("addresses") as? List<Map<String, Any>>
                val cardsMap = documentSnapshot.get("creditCards") as? List<Map<String, Any>>
                val favoriteProductIds =
                    documentSnapshot.get("favoriteProductIds") as ArrayList<String>

                if (addressesMap != null) {
                    for (address in addressesMap) {
                        val addressTitle = address["address_name"] as? String ?: ""
                        val addressDetail = address["address_detail"] as? String ?: ""
                        userAddress.add(Address(addressTitle, addressDetail))
                        Log.e("adres", addressTitle)
                        Log.e("adres", addressDetail)
                    }
                }

                if (cardsMap != null) {
                    for (card in cardsMap) {
                        val cardTitle = card["card_title"] as? String ?: ""
                        val cardNumber = card["card_number"] as? String ?: ""
                        val cardExpDate = card["card_exp_date"] as? String ?: ""
                        val cardCvv = card["card_cvv"] as? String ?: ""
                        userCard.add(Cards(cardTitle, cardNumber, cardExpDate, cardCvv))
                    }
                }
                val fullName = documentSnapshot.getString("fullName") ?: ""
                val phone = documentSnapshot.getString("phoneNumber") ?: ""
                val selectedAddressIndex = documentSnapshot.getString("selectedAddressIndex") ?: ""
                val selectedCardIndex = documentSnapshot.getString("selectedCardIndex") ?: ""


                val intent = Intent(context, MainActivity::class.java)
                val user = Users(
                    fullName,
                    phone,
                    favoriteProductIds,
                    selectedAddressIndex,
                    selectedCardIndex,
                    userAddress,
                    userCard
                )
                intent.putExtra("user", user)
                context.startActivity(intent)
                fragment.requireActivity().finish()


            }
        }
    }

    suspend fun uploadFavs(user: Users): MutableLiveData<List<Favs>> {
        collectionUser.document(auth.currentUser!!.uid).addSnapshotListener { value, error ->
            if (value != null || error != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    val foods = uploadFoods()
                    user.user_favs = value?.get("favoriteProductIds") as ArrayList<String>
                    val favListUser = ArrayList<Favs>()
                    favListUser.clear()
                    for (i in user.user_favs) {
                        for (f in foods) {
                            if (f.yemek_id == i.toInt()) {
                                favListUser.add(
                                    Favs(
                                        i.toInt(),
                                        f.yemek_adi,
                                        f.yemek_resim_adi,
                                        f.yemek_fiyat
                                    )
                                )
                            }
                        }
                    }
                    favList.postValue(favListUser)
                }
            }
        }

        return this.favList
    }


    suspend fun uploadBasket(user_email: String): List<Basket> =
        withContext(Dispatchers.IO) {
            return@withContext fdao.getBasket(user_email).sepet_yemekler
        }

    suspend fun removeItemBasket(basket_id: Int, user_name: String) {
        fdao.removeFoodFromBasket(basket_id, user_name)
        Log.e("Basket Item Remove", "$basket_id removed from basket")
    }

    fun register(context: Context, fragment: Fragment, full_name: String, email: String, phone: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // kullanıcı oluşturuldu
                    val userFirebase = FirebaseAuth.getInstance().currentUser
                    val userDoc = collectionUser.document(userFirebase!!.uid)

                    val userData = hashMapOf(
                        "fullName" to full_name,
                        "phoneNumber" to phone,
                        "selectedAddressIndex" to "0",
                        "selectedCardIndex" to "0",
                        "favoriteProductIds" to listOf<String>(),
                        "addresses" to listOf<String>(),
                        "creditCards" to listOf<String>()
                    )
                    userDoc.set(userData).addOnCompleteListener {
                        Log.e("Firestore", "Kullanıcı bilgileri başarıyla kaydedildi.")
                        val intent = Intent(context, MainActivity::class.java)
                        val user = Users(
                            full_name.trim(),
                            phone.trim(),
                            ArrayList(),
                            "0",
                            "0",
                            ArrayList(),
                            ArrayList(),
                        )
                        intent.putExtra("user", user)
                        context.startActivity(intent)
                        fragment.requireActivity().finish()
                    }.addOnFailureListener { e ->
                        Log.e("Firestore", "kullanıcı bilgileri kaydedilirken hata oluştu!", e)
                    }
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(context, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun removeFav(foodId: Int) {
        val userRef = collectionUser.document(auth.currentUser!!.uid)
        userRef.update("favoriteProductIds", FieldValue.arrayRemove(foodId.toString()))
    }

    fun addFav(foodId: Int) {
        val userRef = collectionUser.document(auth.currentUser!!.uid)
        userRef.update("favoriteProductIds", FieldValue.arrayUnion(foodId.toString()))
    }
}