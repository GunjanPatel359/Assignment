package com.example.assignment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.adapter.FoodItemsAdapter
import com.example.assignment.adapter.MenuAdapter
import com.example.assignment.api.FoodItemsResponse
import com.example.assignment.api.RetrofitInstance
import com.example.assignment.databinding.FragmentMenuBottomSheetBinding
import com.example.assignment.dataclasses.FoodItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentMenuBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater,container,false)
        binding.buttonBack.setOnClickListener{
            dismiss()
        }
        fetchPopularItems()
        return binding.root
    }

    companion object {
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
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter
    }
}