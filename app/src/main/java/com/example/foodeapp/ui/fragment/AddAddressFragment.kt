package com.example.foodeapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Address
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentAddAddressBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddAddressFragment : Fragment() {

    private lateinit var binding: FragmentAddAddressBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var user: Users
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_address, container, false)
        binding.addAddressFragment = this
        auth = Firebase.auth
        user = requireActivity().intent.getSerializableExtra("user") as Users
        return binding.root
    }

    fun backButton(){
        findNavController().navigateUp()
    }

    fun buttonAddAddress(addressLine1: String, addressLine2: String, zipCode: String, city: String, country: String){

        val alertView = layoutInflater.inflate(R.layout.alert_view,null)
        val setTitleEditText = alertView.findViewById<EditText>(R.id.promoEditText)

        MaterialAlertDialogBuilder(requireContext())
            .setView(alertView)
            .setTitle(getString(R.string.address_title))
            .setPositiveButton(R.string.add) { dialog, which ->
                val db = Firebase.firestore
                val addressName = setTitleEditText.text.toString().trim()
                if(addressName.isEmpty() || addressLine1.isEmpty() || addressLine2.isEmpty() || zipCode.isEmpty() || city.isEmpty()){
                    Snackbar.make(requireView(), R.string.fill_in_all_fields, Snackbar.LENGTH_SHORT).show()
                    return@setPositiveButton
                }else{
                    val address = Address(addressName, "$addressLine1 $addressLine2, $zipCode, $city, $country")
                    user.user_adress.add(address)
                    val a = user.user_adress


                    val addressDetail = hashMapOf(
                        "address_name" to addressName,
                        "address_detail" to address.address_detail
                    )

                    db.collection("users").document(auth.currentUser!!.uid).update("addresses", FieldValue.arrayUnion(addressDetail)).addOnCompleteListener {
                        Log.e("Firestore", "Adres bilgileri başarıyla kaydedildi.")
                        findNavController().navigateUp()
                    }.addOnFailureListener{ e ->
                        Log.e("Firestore", "Adres bilgileri kaydedilirken hata oluştu!", e)
                    }
                }
            }
            .setNegativeButton(R.string.cancel) { dialog, which ->

            }.show()
    }
}