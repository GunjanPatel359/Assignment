package com.example.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.adapter.MenuAdapter
import com.example.assignment.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

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
        val menuFoodName= listOf("Burger","sandwich","momo","item","sandwich","momo","Burger","sandwich","momo","item","sandwich","momo")
        val menuItemPrice= listOf("$5","$10","$7","$8","$4","$9","$5","$10","$7","$8","$4","$9")
        val menuImage= listOf(
            R.drawable.food6,
            R.drawable.food5,
            R.drawable.food4,
            R.drawable.food3,
            R.drawable.food2,
            R.drawable.food1,
            R.drawable.food6,
            R.drawable.food5,
            R.drawable.food4,
            R.drawable.food3,
            R.drawable.food2,
            R.drawable.food1
        )
        val adapter = MenuAdapter(ArrayList(menuFoodName),ArrayList(menuItemPrice),ArrayList(menuImage))
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter
        return binding.root
    }

    companion object {
    }
}