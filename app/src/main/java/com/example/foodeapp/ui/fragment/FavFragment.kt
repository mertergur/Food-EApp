package com.example.foodeapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Basket
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.databinding.FragmentFavBinding
import com.example.foodeapp.ui.adapter.BasketAdapter
import com.example.foodeapp.ui.adapter.FavAdapter


class FavFragment : Fragment() {

    private lateinit var binding: FragmentFavBinding
    private var favList = ArrayList<Foods>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavBinding.inflate(inflater, container, false)

        val foodList = ArrayList<Foods>()
        val f1 = Foods(1, "Ayran", "ayran", 25,false)
        val f2 = Foods(2, "Baklava", "baklava", 85,true)
        val f3 = Foods(3, "Fanta", "fanta", 25,true)
        val f4 = Foods(4, "Izgara Somon", "izgarasomon", 200,false)
        val f5 = Foods(5, "Izgara Tavuk", "izgaratavuk", 170,false)
        val f6 = Foods(6, "Kadayıf", "kadayif", 75,false)
        val f7 = Foods(7, "Kahve", "kahve", 70,true)
        val f8 = Foods(8, "Köfte", "kofte", 150,true)
        val f9 = Foods(9, "Lazanya", "lazanya", 135,true)
        val f10 = Foods(10, "Makarna", "makarna", 110,true)
        val f11 = Foods(11, "Pizza", "pizza", 130,false)
        val f12 = Foods(12, "Su", "su", 15,false)
        val f13 = Foods(13, "Sütlaç", "sutlac", 75,true)
        val f14 = Foods(14, "Tiramisu", "tiramisu", 75,false)

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

        for(i in foodList){
            if(i.is_favoirte == true){
                favList.add(i)
            }
        }




        binding.favRV.layoutManager = LinearLayoutManager(requireContext())

        val favAdapter = FavAdapter(requireContext(),favList)
        binding.favRV.adapter = favAdapter

        return binding.root
    }
}