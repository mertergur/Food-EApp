package com.example.foodeapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Basket
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.databinding.BasketCardDesignBinding
import com.example.foodeapp.databinding.FoodCardDesignBinding
import com.example.foodeapp.ui.viewmodel.BasketViewModel
import com.google.android.material.snackbar.Snackbar


class BasketAdapter(
    var data: List<Basket>,
    val onItemClick: (Basket) -> Unit,
): RecyclerView.Adapter<BasketAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BasketCardDesignBinding.inflate(inflater, parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerViewHolder(private val binding: BasketCardDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Basket) {
            binding.basketItemName.text = item.yemek_adi
            binding.basketItemPrice.text = binding.basketItemPrice.context.getString(
                R.string.price,
                (item.yemek_fiyat).toString()
            )
            binding.basketItemCount.text = item.yemek_siparis_adet.toString()


            val url = "http://kasimadalan.pe.hu/yemekler/resimler/${item.yemek_resim_adi}"
            Glide.with(binding.basketItemImage.context).load(url).override(256, 256)
                .into(binding.basketItemImage)

            binding.basketItemDelete.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }
}

/*class BasketAdapter(var mContext: Context, var basketList: List<Basket>, var viewModel: BasketViewModel): RecyclerView.Adapter<BasketAdapter.BasketCardViewHolder>() {

    inner class BasketCardViewHolder(var view: BasketCardDesignBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketCardViewHolder {
        val binding: BasketCardDesignBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.basket_card_design, parent, false)
        return BasketCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketCardViewHolder, position: Int) {
        val basketItem = basketList[position]
        val v = holder.view

        v.basketObject = basketItem
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${basketItem.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(256,256).into(v.basketItemImage)

        v.basketItemDelete.setOnClickListener {
            Snackbar.make(it, "${basketItem.yemek_adi} silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("Evet"){
                    viewModel.removeItemBasket(basketItem.sepet_yemek_id, basketItem.kullanici_adi)
                }
                .setActionTextColor(ContextCompat.getColor(mContext, R.color.primary))
                .show()
        }


    }

    override fun getItemCount(): Int {
        return basketList.size
    }

}*/