package com.example.foodeapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Basket
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentBasketBinding
import com.example.foodeapp.ui.adapter.BasketAdapter
import com.example.foodeapp.ui.viewmodel.BasketViewModel


class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding
    private lateinit var viewModel: BasketViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        binding.basketFragment = this

        val user = requireActivity().intent.getSerializableExtra("user") as Users
        viewModel.user = user
        viewModel.uploadBasket()

        viewModel.basketList.observe(viewLifecycleOwner){

            val basketAdapter = BasketAdapter(requireContext(),it,viewModel)
            binding.basketAdapter = basketAdapter

            var totalPrice = 0
            for (i in it){
                totalPrice += i.yemek_fiyat * i.yemek_siparis_adet
            }
            binding.basketPrice = totalPrice.toString()
        }



        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun checkoutButton(){
        println("checkout")
    }
}