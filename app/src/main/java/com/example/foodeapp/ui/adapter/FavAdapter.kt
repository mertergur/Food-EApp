package com.example.foodeapp.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Favs
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.databinding.BasketCardDesignBinding
import com.example.foodeapp.databinding.FavCardDesignBinding
import com.example.foodeapp.databinding.FoodCardDesignBinding
import com.example.foodeapp.ui.viewmodel.FavViewModel
import com.google.android.material.snackbar.Snackbar


class FavAdapter(
    private var data: List<Favs>,
    val onItemUnFav: (Favs) -> Unit,
): RecyclerView.Adapter<FavAdapter.RecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavCardDesignBinding.inflate(inflater, parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerViewHolder(private val binding: FavCardDesignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Favs) {
            binding.favItemName.text = item.fav_yemek_adi
            binding.favItemPrice.text = binding.favItemPrice.context.getString(R.string.price, item.fav_yemek_fiyat.toString())


            val url = "http://kasimadalan.pe.hu/yemekler/resimler/${item.yemek_resim_adi}"
            Glide.with(binding.favItemImage.context).load(url).override(256,256).into(binding.favItemImage)


            binding.favItemDelete.setOnClickListener {
                onItemUnFav.invoke(item)
            }

        }
    }
}


/*
class FavAdapter(var mContext: Context, var favList: List<Favs>, var viewModel: FavViewModel): RecyclerView.Adapter<FavAdapter.FavCardViewHolder>() {

    inner class FavCardViewHolder(var view: FavCardDesignBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavCardViewHolder {
        val binding: FavCardDesignBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.fav_card_design, parent, false)
        return FavCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavCardViewHolder, position: Int) {
        val favItem = favList[position]
        val v = holder.view
        v.favObject = favItem

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${favItem.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(256,256).into(v.favItemImage)


        v.favItemDelete.setOnClickListener {
            Snackbar.make(it, "${favItem.fav_yemek_adi} silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("Evet"){
                    viewModel.removeFav(favItem.fav_id)
                }
                .setActionTextColor(ContextCompat.getColor(mContext, R.color.primary))
                .show()
        }
    }

    override fun getItemCount(): Int {
        return favList.size
    }
}*/
