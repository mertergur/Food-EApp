package com.example.foodeapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Cards
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentAddCardBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddCardFragment : Fragment() {

    private lateinit var binding: FragmentAddCardBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var user: Users
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_card, container, false)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = requireActivity().intent.getSerializableExtra("user") as Users
        binding.addCardFragment = this

        binding.cardNumberEditText.addTextChangedListener{
            val cardNumber = it.toString()
            binding.cardNumbers.text = cardNumber
            if(cardNumber.length == 4 || cardNumber.length == 9 || cardNumber.length == 14){
                binding.cardNumberEditText.setText("$cardNumber ")
                binding.cardNumberEditText. setSelection( cardNumber.length + 1)
            }
        }

        binding.cvvEditText.addTextChangedListener{
            val cvv = it.toString()
            binding.cardCVV.text = cvv
        }

        binding.expiryDateEditText.addTextChangedListener {
            val expiryDate = it.toString()
            binding.cardExpDate.text = expiryDate
            if(expiryDate.length == 2){
                binding.expiryDateEditText.setText("$expiryDate/")
                binding.expiryDateEditText.setSelection(expiryDate.length + 1)
            }
        }

    }

    fun backButton(){
        findNavController().navigateUp()
    }

    fun buttonAddCard(cardNumber: String, expiryDate: String, cvv: String){
        val alertView = layoutInflater.inflate(R.layout.alert_view,null)
        val alertEditText = alertView.findViewById<EditText>(R.id.promoEditText)

        MaterialAlertDialogBuilder(requireContext())
            .setView(alertView)
            .setTitle(getString(R.string.card_title))
            .setPositiveButton(R.string.add) { dialog, which ->

                val db = Firebase.firestore

                val cardTitle = alertEditText.text.toString().trim()
                if(cardTitle.isEmpty() || cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()){
                    Snackbar.make(requireView(), R.string.fill_in_all_fields,Snackbar.LENGTH_SHORT).show()
                    return@setPositiveButton
                }else{
                    val card = Cards(cardTitle, cardNumber, expiryDate, cvv)
                    user.user_cards.add(card)

                    val cardDetail = hashMapOf(
                        "card_title" to card.card_title,
                        "card_number" to card.card_number,
                        "card_exp_date" to card.card_exp_date,
                        "card_cvv" to card.card_cvv
                    )
                    db.collection("users").document(auth.currentUser!!.uid)
                        .update("creditCards", FieldValue.arrayUnion(cardDetail)).addOnCompleteListener {
                            findNavController().navigateUp()
                        }.addOnFailureListener { e ->
                            Log.e("Firestore", "Kart bilgileri kaydedilirken hata oluştu!", e)
                        }
                }
            }
            .setNegativeButton(R.string.cancel) { dialog, which ->

            }.show()
    }
}