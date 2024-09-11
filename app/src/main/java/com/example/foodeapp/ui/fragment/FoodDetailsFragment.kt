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
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Favs
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentFoodDetailsBinding
import com.example.foodeapp.ui.viewmodel.FoodDetailsViewModel
import com.example.foodeapp.ui.viewmodel.HomePageViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFoodDetailsBinding
    private lateinit var viewModel: FoodDetailsViewModel
    private  var favIsChecked = false
    private var counter = 1
    private var favList = ArrayList<Favs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_details, container, false)
        binding.foodDetailsFragment = this

        binding.foodCounter = counter.toString()
        val bundle: FoodDetailsFragmentArgs by navArgs()
        val food = bundle.food
        val user = bundle.user

        binding.subTotalPrice = (food.yemek_fiyat * counter).toString()
        binding.foodObject = food
        binding.userObject = user


        binding.foodDetailsImageView.setImageResource(
            resources.getIdentifier(
                food.yemek_resim_adi,
                "drawable",
                requireContext().packageName
            )
        )

        uploadFav(food)




        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodDetailsViewModel by viewModels()
        viewModel = tempViewModel
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

    fun favButton(food: Foods){
        var favedItem = Favs(1,food.yemek_adi,food.yemek_resim_adi,food.yemek_fiyat,1)

        favIsChecked = !favIsChecked
        if(favIsChecked){
            binding.foodFavButton.setImageResource(R.drawable.fav_24)
            favList.add(favedItem)
            println(favedItem.fav_id.toString())
            Snackbar.make(requireView(),"${food.yemek_adi} favorilere eklendi.",Snackbar.LENGTH_SHORT).show()
        }else{
            binding.foodFavButton.setImageResource(R.drawable.unfav_24)
            favList.remove(favedItem)
            Snackbar.make(requireView(),"${food.yemek_adi} favorilerden kaldırıldı.",Snackbar.LENGTH_SHORT).show()

        }
    }

    fun addBasketButton(yemek_adi: String, yemek_resim_adi:String, yemek_fiyat: String, yemek_siparis_adet: String, kullanici_adi: String){
        viewModel.addBasket(yemek_adi, yemek_resim_adi, yemek_fiyat.toInt(), yemek_siparis_adet.toInt(),kullanici_adi)
    }

    fun uploadFav(food: Foods){
    }

}