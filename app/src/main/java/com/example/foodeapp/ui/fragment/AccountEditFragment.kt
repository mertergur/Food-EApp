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
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentAccountEditBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AccountEditFragment : Fragment() {
    private lateinit var binding: FragmentAccountEditBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account_edit, container, false)
        binding.accountEditFragment = this
        auth = Firebase.auth

        return binding.root
    }


    fun backButton(){
        findNavController().navigateUp()
    }

    fun goToChangePassword(){
        findNavController().navigate(R.id.action_accountEditFragment_to_changePasswordFragment)

    }

    fun buttonDeleteAccount() {
        Snackbar.make(requireView(), R.string.are_you_sure_acc_delete, Snackbar.LENGTH_LONG)
            .setAction(R.string.yes) {
                val intent = Intent(context, AuthActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
                val db = Firebase.firestore
                val currentUser = FirebaseAuth.getInstance().currentUser
                val userDoc = db.collection("users").document(currentUser!!.uid)
                userDoc.delete()
                auth.currentUser!!.delete()
            }
            .show()
    }

    fun buttonUpdate(firstNameEditText: TextInputEditText, lastNameEditText : TextInputEditText, emailEditText: TextInputEditText) {

        if (auth.currentUser!!.email == emailEditText.text.toString()) {
            val db = Firebase.firestore
            val currentUser = FirebaseAuth.getInstance().currentUser
            val userDoc = db.collection("users").document(currentUser!!.uid)

            val firstName = firstNameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()
            userDoc.get().addOnSuccessListener {
                userDoc.update("fullName", "$firstName $lastName").addOnCompleteListener {
                    val user = requireActivity().intent.getSerializableExtra("user") as Users
                    user.user_fullname = "$firstName $lastName"
                    emailEditText.text!!.clear()
                    firstNameEditText.text!!.clear()
                    lastNameEditText.text!!.clear()

                    emailEditText.clearFocus()
                    firstNameEditText.clearFocus()
                    lastNameEditText.clearFocus()
                    Snackbar.make(requireView(), R.string.info_updated, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

        } else {
            Snackbar.make(requireView(), R.string.wrong_email, Snackbar.LENGTH_SHORT).show()
        }
    }
}