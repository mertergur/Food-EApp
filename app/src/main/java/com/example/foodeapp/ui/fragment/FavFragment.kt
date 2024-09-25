package com.example.foodeapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
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
import com.example.foodeapp.ui.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavFragment : Fragment() {

    private lateinit var binding: FragmentFavBinding
    private val viewModel: FavViewModel  by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_fav, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = requireActivity().intent.getSerializableExtra("user") as Users
        viewModel.user = user
        viewModel.uploadFavs()
        allVisibilty(binding.favRV.isEmpty())

        viewModel.favList.observe(viewLifecycleOwner){ list ->
            list?.let {
                val adapter = FavAdapter(
                    data = it,
                    onItemUnFav = { item ->
                        viewModel.removeFav(item.fav_id)
                    }
                )
                binding.favAdapter = adapter
            }
            allVisibilty(viewModel.favList.value.isNullOrEmpty())
        }
    }

    fun allVisibilty(control: Boolean){
        if(control){
            binding.favRV.visibility = View.GONE
            binding.imageViewFavPlaceHolder.visibility = View.VISIBLE
        }else{

            binding.favRV.visibility = View.VISIBLE
            binding.imageViewFavPlaceHolder.visibility = View.GONE
        }
    }
}