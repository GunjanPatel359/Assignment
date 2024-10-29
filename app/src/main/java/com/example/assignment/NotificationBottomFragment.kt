package com.example.assignment

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.adapter.NotificationAdapter
import com.example.assignment.databinding.FragmentNotificationBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class NotificationBottomFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentNotificationBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNotificationBottomBinding.inflate(layoutInflater,container,false)
        val notifications= listOf("Your order has been Canceled successfully","Order has been taken by the driver","Congrats Your order Placed")
        val notificationImages= listOf(R.drawable.sademoji,R.drawable.truck_icon,R.drawable.illustration)
        val adapter= NotificationAdapter(
            ArrayList(notifications),
            ArrayList(notificationImages)
        )
        binding.notificationrecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.notificationrecyclerview.adapter = adapter

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
    }
}