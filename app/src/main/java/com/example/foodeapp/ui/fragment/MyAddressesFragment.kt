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
import com.example.foodeapp.databinding.FragmentMyAddressesBinding
import com.example.foodeapp.ui.adapter.MyAddressesAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MyAddressesFragment : Fragment() {
    private lateinit var binding: FragmentMyAddressesBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_addresses, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myAddressFragment = this
        val user = requireActivity().intent.getSerializableExtra("user") as Users
        auth = Firebase.auth
        binding.myAddressesAdapter = MyAddressesAdapter(
            data = user.user_adress,
            user = user,
            auth = auth
        )

    }

    fun goToAddAddress(){
        findNavController().navigate(R.id.action_myAddressesFragment_to_addAddressFragment)
    }

    fun backButton(){
        findNavController().navigateUp()
    }
}