package com.example.foodeapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.AuthActivity
import com.example.foodeapp.R
import com.example.foodeapp.databinding.FragmentChangePasswordBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangePasswordFragment : Fragment() {
    private lateinit var binding: FragmentChangePasswordBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_change_password, container, false)
        binding.changePassowrdFragment = this
        auth = Firebase.auth

        return binding.root
    }

    fun backButton(){
        findNavController().navigateUp()
    }

    fun buttonChangePassword(oldPassword:String,newPassword:String,confirmPassword:String){

        if(newPassword == confirmPassword){
            val cerdential = EmailAuthProvider.getCredential(auth.currentUser!!.email!!,oldPassword)
            auth.currentUser!!.reauthenticate(cerdential).addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Snackbar.make(requireView(), R.string.password_updated, Snackbar.LENGTH_SHORT).show()
                    auth.currentUser!!.updatePassword(newPassword).addOnSuccessListener {
                        auth.signOut()
                        val intent = Intent(requireContext(), AuthActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }

                        .addOnFailureListener {
                        Snackbar.make(requireView(), R.string.password_could_not_be_updated,Snackbar.LENGTH_SHORT).show()
                    }
                }else{
                    Snackbar.make(requireView(), R.string.old_password_incorrect,Snackbar.LENGTH_SHORT).show()
                }
            }

        }else{
            Snackbar.make(requireView(), R.string.new_password_does_not_match,Snackbar.LENGTH_SHORT).show()
        }

    }
}