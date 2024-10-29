package com.example.assignment.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.CongratsBottomSheet
import com.example.assignment.PayOutActivity
import com.example.assignment.R
import com.example.assignment.adapter.CartAdapter
import com.example.assignment.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCartBinding.inflate(inflater,container,false)
        val cartFoodName= listOf("Burger","sandwich","momo","item","sandwich","momo")
        val cartItemPrice= listOf("$5","$10","$7","$8","$4","$9")
        val cartImage= listOf(
            R.drawable.food6,
            R.drawable.food5,
            R.drawable.food4,
            R.drawable.food3,
            R.drawable.food2,
            R.drawable.food1
        )
        val adapter = CartAdapter(ArrayList(cartFoodName),ArrayList(cartItemPrice),ArrayList(cartImage))
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        binding.proceedButton.setOnClickListener{
            val intent= Intent(requireContext(),PayOutActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    companion object {

    }
}