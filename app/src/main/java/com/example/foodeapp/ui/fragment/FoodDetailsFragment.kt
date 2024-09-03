package com.example.foodeapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodeapp.R
import com.example.foodeapp.databinding.FragmentFoodDetailsBinding
import com.google.android.material.snackbar.Snackbar

class FoodDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFoodDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodDetailsBinding.inflate(inflater, container, false)

        val bundle: FoodDetailsFragmentArgs by navArgs()
        val food = bundle.food
        var favIsChecked = false

        binding.foodDetailsImageView.setImageResource(
            resources.getIdentifier(
                food.yemek_resim_adi,
                "drawable",
                requireContext().packageName
            )
        )
        binding.foodDetailsNameTextView.text = food.yemek_adi
        binding.subTotalTextView.text = "${food.yemek_fiyat} ₺"

        binding.foodAddButton.setOnClickListener {
            val newCount = binding.foodCounterTextView.text.toString().toInt() + 1
            binding.foodCounterTextView.text = newCount.toString()

            binding.subTotalTextView.text = "${food.yemek_fiyat * newCount} ₺"
        }

        binding.foodSubtractButton.setOnClickListener {
            if(binding.foodCounterTextView.text.toString().toInt() > 1){
                val newCount = binding.foodCounterTextView.text.toString().toInt() - 1
                binding.foodCounterTextView.text = newCount.toString()

                binding.subTotalTextView.text = "${food.yemek_fiyat * newCount} ₺"
            }
        }

        binding.foodFavButton.setOnClickListener {
            favIsChecked = !favIsChecked
            if(favIsChecked){
                binding.foodFavButton.setImageResource(R.drawable.fav_24)
            }else{
                binding.foodFavButton.setImageResource(R.drawable.unfav_24)
            }
        }

        binding.addBasketButton.setOnClickListener {
            Snackbar.make(it, "${food.yemek_adi} sepete eklendi", Snackbar.LENGTH_SHORT).show()
        }

        binding.foodDetailsBackButton.setOnClickListener {
            findNavController().navigateUp()
        }


        return binding.root
    }
}