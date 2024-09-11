package com.example.foodeapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Favs
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentFavBinding
import com.example.foodeapp.ui.adapter.FavAdapter
import com.example.foodeapp.ui.adapter.FoodsAdapter
import com.example.foodeapp.ui.viewmodel.FavViewModel


class FavFragment : Fragment() {

    private lateinit var binding: FragmentFavBinding
    private lateinit var viewModel: FavViewModel
    private var favList = ArrayList<Favs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_fav, container, false)
        val user = requireActivity().intent.getSerializableExtra("user") as Users
        viewModel.user = user
        viewModel.uploadFavs()

        viewModel.favList.observe(viewLifecycleOwner){
            val favAdapter = FavAdapter(requireContext(),it,viewModel)
            binding.favAdapter = favAdapter
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FavViewModel by viewModels()
        viewModel = tempViewModel
    }
}