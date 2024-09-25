package com.example.foodeapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentCheckoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CheckoutFragment : Fragment() {
    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var user: Users
    private lateinit var auth: FirebaseAuth
    private lateinit var args: CheckoutFragmentArgs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args = CheckoutFragmentArgs.fromBundle(requireArguments())

        binding.checkoutFragment = this
        auth = Firebase.auth
        user = requireActivity().intent.getSerializableExtra("user") as Users

        if(user.user_adress.isEmpty() ){
            binding.textViewDeliverTo.setTextColor(requireContext().getColor(R.color.error))
            binding.textViewDeliverTo.text = getString(R.string.please_select)
            binding.textViewDeliverTo.setOnClickListener {
                findNavController().navigate(R.id.action_checkoutFragment_to_profileFragment)
            }
        }else{
            binding.textViewDeliverTo.setTextColor(requireContext().getColor(R.color.textColor))
            binding.textViewDeliverTo.text = user.user_adress[user.selected_address_index.toInt()].address_name
        }

        if(user.user_cards.isEmpty() ) {
            binding.textViewPaymentMethod.text = getString(R.string.please_select)
            binding.textViewPaymentMethod.setTextColor(requireContext().getColor(R.color.error))
            binding.textViewPaymentMethod.setOnClickListener {
                findNavController().navigate(R.id.action_checkoutFragment_to_profileFragment)
            }
        }else{
            binding.textViewPaymentMethod.setTextColor(requireContext().getColor(R.color.textColor))
            binding.textViewPaymentMethod.text = "XXXX XXXX XXXX ${user.user_cards[user.selected_card_index.toInt()].card_number.split(" ").last()}"
        }
        binding.textViewPrice.text = getString(R.string.price, args.totalPrice.toString())


    }

    fun backButton() {
        findNavController().navigateUp()
    }

    fun buttonConfirmOrder(){
        if(user.user_adress.isEmpty() || user.user_cards.isEmpty()){
            Snackbar.make(requireView(), R.string.please_select_address_and_card, Snackbar.LENGTH_SHORT).show()
            return
        }else{
            findNavController().navigate(R.id.action_checkoutFragment_to_orderSuccessFragment)
        }
    }

    fun buttonAddPromo(){
        val alertView = layoutInflater.inflate(R.layout.alert_view,null)
        val promoEditText = alertView.findViewById<EditText>(R.id.promoEditText)

        MaterialAlertDialogBuilder(requireContext())
            .setView(alertView)
            .setTitle(getString(R.string.add_promotion_code))
            .setPositiveButton(R.string.add) { dialog, which ->
                val addedPromoCode = promoEditText.text.toString()
                if (addedPromoCode == "NEW50") {
                    binding.textViewPrice.text = getString(R.string.price, (args.totalPrice * 0.5).toString())
                }else{
                    Snackbar.make(requireView(), R.string.invalid_promo, Snackbar.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(R.string.cancel) { dialog, which ->
            }.show()
    }
}
