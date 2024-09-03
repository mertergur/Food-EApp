package com.example.foodeapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_page, container, false)



        val foodList = ArrayList<Foods>()
        val f1 = Foods(1, "Ayran", "ayran", 25)
        val f2 = Foods(2, "Baklava", "baklava", 85)
        val f3 = Foods(3, "Fanta", "fanta", 25)
        val f4 = Foods(4, "Izgara Somon", "izgarasomon", 200)
        val f5 = Foods(5, "Izgara Tavuk", "izgaratavuk", 170)
        val f6 = Foods(6, "Kadayıf", "kadayif", 75)
        val f7 = Foods(7, "Kahve", "kahve", 70)
        val f8 = Foods(8, "Köfte", "kofte", 150)
        val f9 = Foods(9, "Lazanya", "lazanya", 135)
        val f10 = Foods(10, "Makarna", "makarna", 110)
        val f11 = Foods(11, "Pizza", "pizza", 130)
        val f12 = Foods(12, "Su", "su", 15)
        val f13 = Foods(13, "Sütlaç", "sutlac", 75)
        val f14 = Foods(14, "Tiramisu", "tiramisu", 75)


        foodList.add(f1)
        foodList.add(f2)
        foodList.add(f3)
        foodList.add(f4)
        foodList.add(f5)
        foodList.add(f6)
        foodList.add(f7)
        foodList.add(f8)
        foodList.add(f9)
        foodList.add(f10)
        foodList.add(f11)
        foodList.add(f12)
        foodList.add(f13)
        foodList.add(f14)

        binding.HomeSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { search(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {search(newText)}
                return true
            }

        })

        val foodsAdapter = FoodsAdapter(requireContext(),foodList)
        binding.foodsAdapter = foodsAdapter

        return binding.root
    }

    fun search(searchKeyWord: String){
        Log.e("food search",searchKeyWord)
    }

}