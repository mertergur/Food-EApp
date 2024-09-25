package com.example.foodeapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Address
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.MyAddressesDesignBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyAddressesAdapter(
    var data: List<Address>,
    var user: Users,
    var auth: FirebaseAuth
): RecyclerView.Adapter<MyAddressesAdapter.RecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MyAddressesDesignBinding.inflate(inflater,parent,false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerViewHolder(private val binding: MyAddressesDesignBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Address){
            binding.textViewAddressTitle.text = item.address_name
            binding.textViewAddressDetails.text = item.address_detail

            if(user.selected_address_index == adapterPosition.toString()){
                binding.textViewAddressTitle.setTextColor(ContextCompat.getColor(binding.textViewAddressTitle.context,R.color.secondary))
                binding.textViewAddressDetails.setTextColor(ContextCompat.getColor(binding.textViewAddressDetails.context,R.color.textColor))
            } else{
                binding.textViewAddressTitle.setTextColor(ContextCompat.getColor(binding.textViewAddressTitle.context,R.color.textColor))
                binding.textViewAddressDetails.setTextColor(ContextCompat.getColor(binding.textViewAddressDetails.context,R.color.textColor))
            }

            binding.cardAddress.setOnClickListener {
                user.selected_address_index = adapterPosition.toString()
                notifyDataSetChanged()
                val db = Firebase.firestore
                val userRef = db.collection("users").document(auth.currentUser!!.uid)
                userRef.update("selectedAddressIndex", user.selected_address_index)
            }
        }
    }

}