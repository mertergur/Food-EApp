package com.example.foodeapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Basket
import com.example.foodeapp.databinding.BasketCardDesignBinding
import com.example.foodeapp.ui.viewmodel.BasketViewModel
import com.google.android.material.snackbar.Snackbar

class BasketAdapter(var mContext: Context, var basketList: List<Basket>, var viewModel: BasketViewModel): RecyclerView.Adapter<BasketAdapter.BasketCardViewHolder>() {

    inner class BasketCardViewHolder(var view: BasketCardDesignBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketCardViewHolder {
        val binding: BasketCardDesignBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.basket_card_design, parent, false)
        return BasketCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketCardViewHolder, position: Int) {
        val basketItem = basketList[position]
        val v = holder.view

        v.basketObject = basketItem
        v.basketItemImage.setImageResource(mContext.resources.getIdentifier(basketItem.yemek_resim_adi, "drawable", mContext.packageName))

        v.basketItemDelete.setOnClickListener {
            Snackbar.make(it, "${basketItem.yemek_adi} silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("Evet"){
                    viewModel.removeItemBasket(basketItem.sepet_yemek_id)
                }
                .setActionTextColor(ContextCompat.getColor(mContext, R.color.primary))
                .show()
        }


    }

    override fun getItemCount(): Int {
        return basketList.size
    }

}