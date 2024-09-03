package com.example.foodeapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodeapp.R
import com.example.foodeapp.databinding.FragmentFoodDetailsBinding
import com.google.android.material.snackbar.Snackbar

class FoodDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFoodDetailsBinding
    private  var favIsChecked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_details, container, false)
        binding.foodDetailsFragment = this

        val bundle: FoodDetailsFragmentArgs by navArgs()
        val food = bundle.food
        binding.foodObject = food


        binding.foodDetailsImageView.setImageResource(
            resources.getIdentifier(
                food.yemek_resim_adi,
                "drawable",
                requireContext().packageName
            )
        )



        return binding.root
    }

    fun subTotalButton(choice: Char, price: Int, oldCount: String){
        var newCount: Int
        if(choice == '+') {
            newCount = oldCount.toInt() + 1
        }else{
            newCount = oldCount.toInt() - 1
        }
        binding.foodCounterTextView.text = newCount.toString()
        binding.subTotalTextView.text = "${price * newCount} ₺"
    }

    fun backButton(){
        findNavController().navigateUp()
    }

    fun favButton(){
        favIsChecked = !favIsChecked
        if(favIsChecked){
            binding.foodFavButton.setImageResource(R.drawable.fav_24)
        }else{
            binding.foodFavButton.setImageResource(R.drawable.unfav_24)
        }
    }

    fun addBasketButton(yemek_adet:String, yemek_adi: String, yemek_fiyat: String){
        Log.e("addBasket","$yemek_adet adet $yemek_adi $yemek_fiyat karşılığında Sepete eklendi.")
    }

}