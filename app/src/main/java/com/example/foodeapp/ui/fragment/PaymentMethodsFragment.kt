package com.example.foodeapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentPaymentMethodsBinding
import com.example.foodeapp.ui.adapter.MyPaymentAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PaymentMethodsFragment : Fragment() {
    private lateinit var binding: FragmentPaymentMethodsBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_payment_methods, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = requireActivity().intent.getSerializableExtra("user") as Users
        binding.paymentMethodsFragment = this
        auth = Firebase.auth
        binding.myMethodsAdapter = MyPaymentAdapter(
            data = user.user_cards,
            user = user,
            auth = auth
        )
    }

    fun goToAddCard(){
        findNavController().navigate(R.id.action_paymentMethodsFragment_to_addCardFragment)

    }

    fun backButton(){
        findNavController().navigateUp()
    }
}