package com.example.foodeapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.MainActivity
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Address
import com.example.foodeapp.data.entity.Cards
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentRegisterBinding
import com.example.foodeapp.ui.viewmodel.RegisterViewModel
import com.example.foodeapp.util.coloredString
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.registerFragment = this

        val selectedColor = R.color.secondary
        val coloredPart = binding.textViewGoToLogin.text.split(" ").last().length
        binding.textViewGoToLogin.coloredString(binding.textViewGoToLogin,selectedColor,coloredPart,requireContext())

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: RegisterViewModel by viewModels()
        viewModel = tempViewModel

        auth = Firebase.auth
    }

    fun registerButton(email: String, full_name: String, phone: String, password: String){
        viewModel.register(requireContext(), this@RegisterFragment, email,full_name,phone,password)
    }

    fun goBack(){
        findNavController().navigateUp()
    }
}