package com.example.foodeapp.ui.fragment

import android.content.Context
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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.MainActivity
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Address
import com.example.foodeapp.data.entity.Cards
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentLoginBinding
import com.example.foodeapp.ui.viewmodel.LoginViewModel
import com.example.foodeapp.util.coloredString
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.loginFragment = this

        binding.textViewLogin.text.toString().uppercase()

        val selectedColor = R.color.secondary
        val coloredPart = binding.textViewGoToRegister.text.split(" ").last().length
        binding.textViewGoToRegister.coloredString(
            binding.textViewGoToRegister,
            selectedColor,
            coloredPart,
            requireContext()
        )
        auth = Firebase.auth

        loginControl()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: LoginViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun loginButton(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
               viewModel.login(requireContext(),this@LoginFragment)
            }.addOnFailureListener {
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun goToRegister() {
        findNavController().navigate(R.id.action_login_to_register)
    }


    fun loginControl(){
        val currentUser = auth.currentUser
        if (currentUser != null) {
            viewModel.login(requireContext(), this@LoginFragment)
        }
    }


    
}
