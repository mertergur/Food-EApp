package com.example.foodeapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Basket
import com.example.foodeapp.databinding.FragmentBasketBinding
import com.example.foodeapp.ui.adapter.BasketAdapter


class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBasketBinding.inflate(inflater, container, false)

        val basketList = ArrayList<Basket>()

        val b1 = Basket(1,"Ayran","ayran",25,10,"mert_ergur")
        val b2 = Basket(2,"Baklava","baklava",85,1,"mert_ergur")
        val b3 = Basket(3,"Fanta","fanta",25,1,"mert_ergur")

        basketList.add(b1)
        basketList.add(b2)
        basketList.add(b3)

        binding.basketRV.layoutManager = LinearLayoutManager(requireContext())
        val basketAdapter = BasketAdapter(requireContext(),basketList)
        binding.basketRV.adapter = basketAdapter

        var totalPrice = 0
        for (i in basketList){
            totalPrice += i.yemek_fiyat * i.yemek_siparis_adet
        }
        binding.textViewTotalPrice.text = "$totalPrice ₺"

        return binding.root



    }
}