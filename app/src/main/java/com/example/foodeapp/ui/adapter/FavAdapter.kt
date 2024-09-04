package com.example.foodeapp.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.databinding.BasketCardDesignBinding

class FavAdapter(var mContext: Context, var favList: ArrayList<Foods>): RecyclerView.Adapter<FavAdapter.FavCardViewHolder>() {

    inner class FavCardViewHolder(var view: BasketCardDesignBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavCardViewHolder {
        val binding = BasketCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return FavCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavCardViewHolder, position: Int) {
        val favItem = favList[position]
        val v = holder.view

        v.basketItemImage.setImageResource(mContext.resources.getIdentifier(favItem.yemek_resim_adi, "drawable", mContext.packageName))
        v.basketItemName.text = favItem.yemek_adi
        v.basketItemPrice.text = "${favItem.yemek_fiyat} ₺"
        v.basketItemDelete.setImageResource(R.drawable.fav_24)
        v.basketItemDelete.setBackgroundColor(Color.TRANSPARENT)
        v.basketItemCount.visibility = View.GONE

        v.basketItemDelete.setOnClickListener {
            Log.e("Unfav Food", "${favItem.yemek_adi} removed from favorites")
        }
    }

    override fun getItemCount(): Int {
        return favList.size
    }


}