package com.example.assignment.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.assignment.MenuBottomSheetFragment
import com.example.assignment.R
import com.example.assignment.adapter.FoodItemsAdapter
import com.example.assignment.api.FoodItemsResponse
import com.example.assignment.api.RetrofitInstance
import com.example.assignment.databinding.FragmentHomeBinding
import com.example.assignment.dataclasses.FoodItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.ViewAllMenu.setOnClickListener{
            val bottomSheetDialog = MenuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager,"Test")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList=ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner_bg_1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner_bg_2,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner_bg_3,ScaleTypes.FIT))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val itemPosition = imageList[position]
                val itemMessage="Selected Image $position"
                Toast.makeText(requireContext(),itemMessage,Toast.LENGTH_SHORT).show()
            }
        })

        binding.PopularRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        fetchPopularItems()
    }

    private fun fetchPopularItems() {
        RetrofitInstance.api.getFoodItems().enqueue(object : Callback<FoodItemsResponse> {
            override fun onResponse(
                call: Call<FoodItemsResponse>,
                response: Response<FoodItemsResponse>
            ) {
                if (response.isSuccessful) {
                    val foodItems = response.body()?.fooditem ?: emptyList()
                    setupRecyclerView(foodItems)
                } else {
                    Log.e("HomeFragment", "Failed to fetch data: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<FoodItemsResponse>, t: Throwable) {
                Log.e("HomeFragment", "Error fetching data", t)
                Toast.makeText(requireContext(), "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView(foodItems: List<FoodItem>) {
        val adapter = FoodItemsAdapter(
            id = foodItems.map { it._id },
            items = foodItems.map { it.name },
            prices = foodItems.map { "${it.price}/-" },
            imageUrls = foodItems.map { it.imageUrl },
            context = requireContext()
        )
        binding.PopularRecyclerView.adapter = adapter
    }
}
