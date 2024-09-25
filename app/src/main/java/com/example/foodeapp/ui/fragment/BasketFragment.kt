package com.example.foodeapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Basket
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentBasketBinding
import com.example.foodeapp.ui.adapter.BasketAdapter
import com.example.foodeapp.ui.viewmodel.BasketViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding
    private lateinit var viewModel: BasketViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_basket, container, false)
        binding.basketFragment = this
        val auth = Firebase.auth

        viewModel.user_email = auth.currentUser!!.email.toString()
        viewModel.uploadBasket()
        allVisibilty(binding.basketRV.isEmpty())
        viewModel.basketList.observe(viewLifecycleOwner){ list ->
            list?.let {
                binding.basketAdapter = BasketAdapter(
                    data = it,
                    onItemClick = { item ->
                        viewModel.removeItemBasket(
                            item.sepet_yemek_id,
                            item.kullanici_adi
                        )
                    }
                )
                var totalPrice = 0
                for (i in list) {
                    totalPrice += i.yemek_fiyat
                }
                binding.basketPrice = totalPrice.toString()
            }
            allVisibilty(viewModel.basketList.value.isNullOrEmpty())
        }

        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun checkoutButton(){
        val action = BasketFragmentDirections.actionBasketFragmentToCheckoutFragment(binding.basketPrice!!.toInt())
        Navigation.findNavController(binding.button).navigate(action)
    }

    fun allVisibilty(control: Boolean){
        if(control){
            binding.textViewTotal.visibility = View.GONE
            binding.textViewTotalPrice.visibility = View.GONE
            binding.button.visibility = View.GONE
            binding.basketRV.visibility = View.GONE
            binding.imageViewEmptyBasket.visibility = View.VISIBLE
        }else{
            binding.textViewTotal.visibility = View.VISIBLE
            binding.textViewTotalPrice.visibility = View.VISIBLE
            binding.button.visibility = View.VISIBLE
            binding.basketRV.visibility = View.VISIBLE
            binding.imageViewEmptyBasket.visibility = View.GONE
        }
    }
}