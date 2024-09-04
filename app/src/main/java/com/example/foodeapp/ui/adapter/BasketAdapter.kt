package com.example.foodeapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodeapp.data.entity.Basket
import com.example.foodeapp.databinding.BasketCardDesignBinding

class BasketAdapter(var mContext: Context, var basketList: List<Basket>): RecyclerView.Adapter<BasketAdapter.BasketCardViewHolder>() {

    inner class BasketCardViewHolder(var view: BasketCardDesignBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketCardViewHolder {
        val binding = BasketCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return BasketCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketCardViewHolder, position: Int) {
        val basketItem = basketList[position]
        val v = holder.view

        v.basketItemImage.setImageResource(mContext.resources.getIdentifier(basketItem.yemek_resim_adi, "drawable", mContext.packageName))
        v.basketItemName.text = basketItem.yemek_adi
        v.basketItemPrice.text = "${basketItem.yemek_fiyat * basketItem.yemek_siparis_adet} ₺"
        v.basketItemCount.text = basketItem.yemek_siparis_adet.toString()

        v.basketItemDelete.setOnClickListener {
            Log.e("Delete Food", "${basketItem.yemek_adi} Silindi")
        }


    }

    override fun getItemCount(): Int {
        return basketList.size
    }

}