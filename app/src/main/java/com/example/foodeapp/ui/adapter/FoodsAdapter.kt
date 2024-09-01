package com.example.foodeapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.databinding.FoodCardDesignBinding
import com.google.android.material.snackbar.Snackbar

class FoodsAdapter(var mContext: Context, var foodsList: List<Foods>): RecyclerView.Adapter<FoodsAdapter.CardViewHolder>() {

    inner class CardViewHolder(var view: FoodCardDesignBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = FoodCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val food = foodsList[position]
        var v = holder.view

        v.cardFoodImageView.setImageResource(mContext.resources.getIdentifier(food.yemek_resim_adi, "drawable", mContext.packageName))
        v.cardFoodNameTxtView.text = food.yemek_adi
        v.cardFoodPriceTxtView.text = "${food.yemek_fiyat} ₺"

        v.cardFoodAddBasketImageView.setOnClickListener {
            Snackbar.make(it, "${food.yemek_adi} sepete eklendi", Snackbar.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return foodsList.size
    }

}