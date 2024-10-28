package com.example.assignment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.R
import com.example.assignment.adapter.BuyAgainAdapter
import com.example.assignment.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
    private lateinit var binding:FragmentHistoryBinding
    private lateinit var buyAgainAdapter: BuyAgainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater,container,false)
        setupRecyclerView()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupRecyclerView(){
        val buyAgainFoodName = arrayListOf("Food 2","Food 3","Food 3")
        val buyAgainFoodPrice = arrayListOf("$10","$20","$25")
        val buyAgainFoodImage = arrayListOf(R.drawable.food4,R.drawable.food1,R.drawable.food6)
        buyAgainAdapter = BuyAgainAdapter(buyAgainFoodName,buyAgainFoodPrice,buyAgainFoodImage)
        binding.BuyAgainRecyclerView.adapter = buyAgainAdapter
        binding.BuyAgainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
    }
}