package com.example.foodeapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FoodCardDesignBinding
import com.example.foodeapp.ui.fragment.HomePageFragmentDirections
import com.example.foodeapp.ui.viewmodel.FoodDetailsViewModel
import com.google.android.material.snackbar.Snackbar


class FoodsAdapter(
    private var data: List<Foods>,
    val onItemClick: (Foods) -> Unit,
    val onAddToCardClick: (Foods) -> Unit,
): RecyclerView.Adapter<FoodsAdapter.RecyclerViewHolder>() {

    fun updateList(newList: List<Foods>) {
        data = newList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FoodCardDesignBinding.inflate(inflater, parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerViewHolder(private val binding: FoodCardDesignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Foods) {
            binding.cardFoodNameTxtView.text = item.yemek_adi
            binding.cardFoodPriceTxtView.text = binding.cardFoodPriceTxtView.context.getString(R.string.price, item.yemek_fiyat.toString())

            val url = "http://kasimadalan.pe.hu/yemekler/resimler/${item.yemek_resim_adi}"
            Glide.with(binding.cardFoodImageView.context).load(url).override(256,256).into(binding.cardFoodImageView)

            binding.root.setOnClickListener {
                onItemClick.invoke(item)
            }

            binding.cardFoodAddBasketImageView.setOnClickListener {
                onAddToCardClick.invoke(item)
            }

        }
    }

}

/*

class FoodsAdapter(var mContext: Context, var foodsList: List<Foods>, var user: Users*/
/*, var viewModel: FoodDetailsViewModel*//*
): RecyclerView.Adapter<FoodsAdapter.CardViewHolder>() {

    inner class CardViewHolder(var view: FoodCardDesignBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding: FoodCardDesignBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.food_card_design, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val food = foodsList[position]
        val v = holder.view

        v.foodObject = food
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(256,256).into(v.cardFoodImageView)


        */
/*v.cardFood.setOnClickListener {

        }*//*


       */
/* v.cardFoodAddBasketImageView.setOnClickListener {
            //yemek_adi, yemek_resim_adi, yemek_fiyat.toInt(), yemek_siparis_adet.toInt(),kullanici_adi
            viewModel.addBasket(food.yemek_adi, food.yemek_resim_adi, food.yemek_fiyat.toInt(),1, user.user_email)
        }
*//*

    }

    override fun getItemCount(): Int {
        return foodsList.size
    }


}*/


