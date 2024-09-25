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
import com.example.foodeapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var snapshotListener: ListenerRegistration
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)


        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = requireActivity().intent.getSerializableExtra("user") as Users
        binding.userNameTitle = user.user_fullname

        binding.cardViewLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(requireContext(), AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        binding.cardViewAccount.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_accountEditFragment)
        }

        binding.cardViewManageAddress.setOnClickListener {
            if(user.user_adress.isEmpty()) {
                findNavController().navigate(R.id.action_profileFragment_to_addAddressFragment)
            }else{
                findNavController().navigate(R.id.action_profileFragment_to_myAddressesFragment)
            }
        }
        binding.cardViewManagePayment.setOnClickListener {
            if(user.user_cards.isEmpty()) {
                findNavController().navigate(R.id.action_profileFragment_to_addCardFragment)
            }else{
                findNavController().navigate(R.id.action_profileFragment_to_paymentMethodsFragment)
            }
        }

        binding.cardViewContact.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_contactDeveloperFragment)
        }

    }
}