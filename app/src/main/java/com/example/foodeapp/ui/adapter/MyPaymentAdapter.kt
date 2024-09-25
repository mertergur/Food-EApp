package com.example.foodeapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Cards
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.MyPaymentMethodsDesignBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyPaymentAdapter(
   var data: List<Cards>,
   var user: Users,
   var auth: FirebaseAuth
    ): RecyclerView.Adapter<MyPaymentAdapter.RecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MyPaymentMethodsDesignBinding.inflate(inflater,parent,false)
        return RecyclerViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerViewHolder(private val binding: MyPaymentMethodsDesignBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Cards){
            binding.textViewCardTitle.text = item.card_title
            binding.textViewCardNumber.text = item.card_number
            binding.textViewCardExp.text = item.card_exp_date

            if(user.selected_card_index == this.adapterPosition.toString()){
                binding.textViewCardTitle.setTextColor(ContextCompat.getColor(binding.textViewCardTitle.context,
                    R.color.secondary))
                binding.textViewCardNumber.setTextColor(ContextCompat.getColor(binding.textViewCardNumber.context,R.color.textColor))
                binding.textViewCardExp.setTextColor(ContextCompat.getColor(binding.textViewCardExp.context,R.color.textColor))
            }else{
                binding.textViewCardTitle.setTextColor(ContextCompat.getColor(binding.textViewCardTitle.context,R.color.textColor))
                binding.textViewCardNumber.setTextColor(ContextCompat.getColor(binding.textViewCardNumber.context,R.color.gray))
                binding.textViewCardExp.setTextColor(ContextCompat.getColor(binding.textViewCardExp.context,R.color.gray))
            }

            binding.card.setOnClickListener {
                user.selected_card_index = adapterPosition.toString()
                notifyDataSetChanged()
                val db = Firebase.firestore
                val userRef = db.collection("users").document(auth.currentUser!!.uid)
                userRef.update("selectedCardIndex", user.selected_card_index)
            }
        }
    }

}