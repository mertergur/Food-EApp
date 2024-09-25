package com.example.foodeapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Favs
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentFoodDetailsBinding
import com.example.foodeapp.ui.viewmodel.FavViewModel
import com.example.foodeapp.ui.viewmodel.FoodDetailsViewModel
import com.example.foodeapp.ui.viewmodel.HomePageViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFoodDetailsBinding
    private val viewModel: FoodDetailsViewModel by viewModels()
    private val viewModelFavs: FavViewModel by viewModels()
    private  var favIsChecked = false
    private var counter = 1
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_details, container, false)
        binding.foodDetailsFragment = this
        auth = Firebase.auth
        val db = Firebase.firestore
        val userRef = db.collection("users").document(auth.currentUser!!.uid)
        val user = requireActivity().intent.getSerializableExtra("user") as Users
        viewModelFavs.user = user


        binding.foodCounter = counter.toString()
        val bundle: FoodDetailsFragmentArgs by navArgs()
        val food = bundle.food

        userRef.get().addOnSuccessListener {
            val faviroteProductIds = it.get("favoriteProductIds") as? MutableList<String> ?: mutableListOf()
            if(faviroteProductIds.contains(food.yemek_id.toString())){
                favIsChecked = true
                binding.foodFavButton.setImageResource(R.drawable.fav_24)
            }else{
                favIsChecked = false
                binding.foodFavButton.setImageResource(R.drawable.unfav_24)
            }
        }

        binding.subTotalPrice = (food.yemek_fiyat * counter).toString()
        binding.foodObject = food

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.yemek_resim_adi}"
        Glide.with(requireContext()).load(url).override(500,700).into(binding.foodDetailsImageView)


        return binding.root
    }


    fun subTotalButton(choice: Char, price: Int, oldCount: String){

        if(choice == '+') {
            counter = oldCount.toInt() + 1
        }else if(choice == '-' && oldCount.toInt() > 1){
            counter = oldCount.toInt() - 1
        }
        binding.foodCounter = counter.toString()
        binding.subTotalPrice = (counter * price).toString()
    }

    fun backButton(){
        findNavController().navigateUp()
    }

    fun favButton(foodId: Int){
        favIsChecked = !favIsChecked
        if(favIsChecked){
            viewModelFavs.addFav(foodId)
            binding.foodFavButton.setImageResource(R.drawable.fav_24)

        }else{
           viewModelFavs.removeFav(foodId)
            binding.foodFavButton.setImageResource(R.drawable.unfav_24)

        }
    }

    fun addBasketButton(yemek_adi: String, yemek_resim_adi:String, yemek_fiyat: String, yemek_siparis_adet: String){
        val userEmail = auth.currentUser?.email
        if(userEmail != null) {
            viewModel.addBasket(
                yemek_adi,
                yemek_resim_adi,
                yemek_fiyat.toInt(),
                yemek_siparis_adet.toInt(),
                userEmail
            )
            Snackbar.make(requireView(), "$yemek_adi  ${getString(R.string.added_to_cart)}", Snackbar.LENGTH_SHORT).show()
        }
    }
}