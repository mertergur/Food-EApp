package com.example.foodeapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.databinding.FragmentHomePageBinding
import com.example.foodeapp.ui.adapter.FoodsAdapter

class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        binding.homeRV.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        val foodList = ArrayList<Foods>()
        val f1 = Foods(1, "Ayran", "ayran", 5)
        val f2 = Foods(2, "Fanta", "ayran", 5)
        val f3 = Foods(3, "Kola", "ayran", 5)
        val f4 = Foods(4, "Su", "ayran", 2)

        foodList.add(f1)
        foodList.add(f2)
        foodList.add(f3)
        foodList.add(f4)

        val foodsAdapter = FoodsAdapter(requireContext(),foodList)
        binding.homeRV.adapter = foodsAdapter

        return binding.root
    }
}