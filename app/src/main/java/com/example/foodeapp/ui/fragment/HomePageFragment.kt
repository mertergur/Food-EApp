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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodeapp.R
import com.example.foodeapp.data.entity.Address
import com.example.foodeapp.data.entity.Favs
import com.example.foodeapp.data.entity.Foods
import com.example.foodeapp.data.entity.Users
import com.example.foodeapp.databinding.FragmentHomePageBinding
import com.example.foodeapp.ui.adapter.FoodsAdapter
import com.example.foodeapp.ui.adapter.MyAddressesAdapter
import com.example.foodeapp.ui.viewmodel.FoodDetailsViewModel
import com.example.foodeapp.ui.viewmodel.HomePageViewModel
import com.example.foodeapp.ui.viewmodel.RegisterViewModel
import com.example.foodeapp.util.coloredString
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private val viewModel: HomePageViewModel by viewModels()
    private val viewModelDetailsFragment: FoodDetailsViewModel by viewModels()
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = requireActivity().intent.getSerializableExtra("user") as Users

        val splitedUserName = user.user_fullname.split(" ").first().replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase()
            else
                it.toString()
        }
        binding.userName = splitedUserName
        binding.textViewHello.text = getString(R.string.hello, binding.userName)
        val selectedColor = R.color.primary
        val coloredPart = binding.textViewHello.text.split(" ").last().length
        binding.textViewHello.coloredString(
            binding.textViewHello,
            selectedColor,
            coloredPart,
            requireContext()
        )

        viewModel.foodList.observe(viewLifecycleOwner) { list ->
            list?.let {
                val adapter = FoodsAdapter(
                    data = it,
                    onItemClick = { item ->
                        val direction = HomePageFragmentDirections.actionHomePageFragmentToFoodDetailsFragment2(food = item, user = user)
                        val navController = Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        navController.navigate(direction)
                    },
                    onAddToCardClick = { item ->
                        viewModelDetailsFragment.addBasket(
                            item.yemek_adi,
                            item.yemek_resim_adi,
                            item.yemek_fiyat,
                            1,
                            auth.currentUser?.email!!
                        )
                        Snackbar.make(requireView(), "${item.yemek_adi} ${getString(R.string.added_to_cart)}", Snackbar.LENGTH_SHORT).show()

                    },
                )
                binding.foodsAdapter = adapter
                adapter.updateList(list)
            }
        }

        if(user.selected_address_index.toInt() < user.user_adress.size){
            val addressTitle = user.user_adress[user.selected_address_index.toInt()].address_name
            binding.adressTitle = addressTitle
        }else{
            binding.adressTitle = ""
        }



       binding.HomeSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText ?: "")
                return true
            }
        })

        viewModel.initVM()
    }
}