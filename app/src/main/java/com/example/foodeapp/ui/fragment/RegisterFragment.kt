package com.example.foodeapp.ui.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentRegisterBinding
import com.example.foodeapp.ui.viewmodel.RegisterViewModel
import com.example.foodeapp.util.coloredString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel
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
    }

    fun registerButton(full_name: String, email: String,phone: String, password: String){
        viewModel.register(full_name, email, phone, password)
        findNavController().navigate(R.id.action_register_to_login)
    }

    fun goBack(){
        findNavController().navigateUp()
    }
}