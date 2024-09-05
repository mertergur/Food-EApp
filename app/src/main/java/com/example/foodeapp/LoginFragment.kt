package com.example.foodeapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.textViewGoToRegister.setOnClickListener {
            goToRegister()
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }


        return binding.root
    }

    fun login(){
        val intent = Intent(requireContext(),MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    fun goToRegister(){
        findNavController().navigate(R.id.action_login_to_register)
    }

}