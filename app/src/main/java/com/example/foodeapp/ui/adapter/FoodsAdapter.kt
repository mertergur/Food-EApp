package com.example.foodeapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FoodCardDesignBinding
import com.example.foodeapp.ui.fragment.HomePageFragmentDirections
import com.example.foodeapp.ui.viewmodel.FoodDetailsViewModel
import com.google.android.material.snackbar.Snackbar

class FoodsAdapter(var mContext: Context, var foodsList: List<Foods>, var user: Users, var viewModel: FoodDetailsViewModel): RecyclerView.Adapter<FoodsAdapter.CardViewHolder>() {

    inner class CardViewHolder(var view: FoodCardDesignBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding: FoodCardDesignBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.food_card_design, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val food = foodsList[position]
        val v = holder.view

        v.foodObject = food
        v.cardFoodImageView.setImageResource(mContext.resources.getIdentifier(food.yemek_resim_adi, "drawable", mContext.packageName))


        v.cardFood.setOnClickListener {
            val direction = HomePageFragmentDirections.actionHomePageFragmentToFoodDetailsFragment2(food = food, user = user)
            Navigation.findNavController(it).navigate(direction)
        }

        v.cardFoodAddBasketImageView.setOnClickListener {
            //yemek_adi, yemek_resim_adi, yemek_fiyat.toInt(), yemek_siparis_adet.toInt(),kullanici_adi
            viewModel.addBasket(food.yemek_adi, food.yemek_resim_adi, food.yemek_fiyat,1, user.user_email)
        }

    }

    override fun getItemCount(): Int {
        return foodsList.size
    }


}