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
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Favs
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.databinding.BasketCardDesignBinding
import com.example.foodeapp.databinding.FavCardDesignBinding
import com.example.foodeapp.ui.viewmodel.FavViewModel
import com.google.android.material.snackbar.Snackbar

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

        v.favItemImage.setImageResource(mContext.resources.getIdentifier(favItem.fav_yemek_resim_adi, "drawable", mContext.packageName))


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
}