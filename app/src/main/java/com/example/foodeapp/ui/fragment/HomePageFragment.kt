package com.example.foodeapp.ui.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Favs
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentHomePageBinding
import com.example.foodeapp.ui.adapter.FoodsAdapter
import com.example.foodeapp.ui.viewmodel.FoodDetailsViewModel
import com.example.foodeapp.ui.viewmodel.HomePageViewModel
import com.example.foodeapp.ui.viewmodel.RegisterViewModel
import com.example.foodeapp.util.coloredString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomePageViewModel
    private lateinit var viewModelDetailsFragment: FoodDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
//        val user = Users(1,"Mert Ergür","mert_2626@live.com","05369548515","123123")

        val user = requireActivity().intent.getSerializableExtra("user") as Users


        val userName = user.user_fullname.split(" ").first().replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase()
            else
                it.toString()
        }
        binding.userName = userName
        viewModel.foodList.observe(viewLifecycleOwner) {
            val foodsAdapter =
                FoodsAdapter(requireContext(), it, user, viewModelDetailsFragment)
            binding.foodsAdapter = foodsAdapter
        }

        binding.textViewHello.text = getString(R.string.hello, userName)
        binding.adressTitle = "home".uppercase()

        val selectedColor = R.color.primary
        val coloredPart = binding.textViewHello.text.split(" ").last().length
        binding.textViewHello.coloredString(
            binding.textViewHello,
            selectedColor,
            coloredPart,
            requireContext()
        )

        binding.HomeSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return true
            }
        })


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomePageViewModel by viewModels()
        viewModel = tempViewModel

        val tempViewModelDetailsFragment: FoodDetailsViewModel by viewModels()
        viewModelDetailsFragment = tempViewModelDetailsFragment
    }

    override fun onResume() {
        super.onResume()
        viewModel.uploadFoods()
    }


}