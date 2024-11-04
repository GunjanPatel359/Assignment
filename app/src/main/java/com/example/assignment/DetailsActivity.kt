package com.example.assignment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment.adapter.FoodItemsAdapter
import com.example.assignment.api.FoodItemResponse
import com.example.assignment.api.RetrofitInstance
import com.example.assignment.databinding.ActivityDetailsBinding
import com.example.assignment.dataclasses.FoodItem
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding :ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val foodItemId = intent.getStringExtra("MenuItemId")!!

        binding.detailProgressBar.visibility = View.VISIBLE

        fetchFoodItem(foodItemId)

        binding.imageButton.setOnClickListener{
            finish()
        }
    }

    private fun fetchFoodItem(foodItemId: String) {
        RetrofitInstance.api.getFoodItem(foodItemId).enqueue(object : Callback<FoodItemResponse> {
            override fun onResponse(call: Call<FoodItemResponse>, response: Response<FoodItemResponse>) {
                if (response.isSuccessful) {
                    val foodItem = response.body()?.fooditem!!
                    binding.detailFoodName.text = foodItem.name
                    binding.descriptionTextView.text = foodItem.smallDescription
                    binding.ingredientTextView.text = foodItem.description

                    // Log the URL for debugging
                    val imageUrl = "https://food-app-backend-green.vercel.app/${foodItem.imageUrl}"
                    Log.d("DetailsActivity", "Image URL: $imageUrl")

                    Picasso.get()
                        .load(imageUrl)
                        .fit()
                        .centerCrop()
                        .error(R.color.black)
                        .into(binding.detailFoodImage, object : com.squareup.picasso.Callback {
                            override fun onSuccess() {
                                // Image loaded successfully
                                binding.detailProgressBar.visibility = View.GONE
                            }

                            override fun onError(e: Exception?) {
                                Log.e("DetailsActivity", "Error loading image", e)
                                binding.detailProgressBar.visibility = View.GONE
                            }
                        })
                } else {
                    Log.e("DetailsActivity", "Failed to fetch data: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<FoodItemResponse>, t: Throwable) {
                Toast.makeText(this@DetailsActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }


}