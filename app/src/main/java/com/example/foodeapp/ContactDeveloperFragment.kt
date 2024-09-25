package com.example.foodeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.foodeapp.databinding.FragmentContactDeveloperBinding


class ContactDeveloperFragment : Fragment() {

    private lateinit var binding : FragmentContactDeveloperBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_contact_developer,container,false)
        binding.contactDeveloperFragment = this
        return binding.root
    }

    fun backButton(){
        findNavController().navigateUp()
    }
}