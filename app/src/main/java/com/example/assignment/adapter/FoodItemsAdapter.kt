package com.example.assignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.DetailsActivity
import com.example.assignment.R
import com.example.assignment.databinding.PopularItemBinding
import com.squareup.picasso.Picasso

class FoodItemsAdapter(
    private val id: List<String>,
    private val items: List<String>,
    private val prices: List<String>,
    private val imageUrls: List<String>,  // List of image URLs as strings
    private val context: Context
) : RecyclerView.Adapter<FoodItemsAdapter.FoodItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemsViewHolder {
        return FoodItemsViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FoodItemsViewHolder, position: Int) {
        val item = items[position]
        val imageUrl = imageUrls[position]
        val itemPrice = prices[position]
        holder.bind(item, itemPrice, imageUrl)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("MenuItemId", id[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class FoodItemsViewHolder(private val binding: PopularItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageView = binding.imageView4 // Reference to your ImageView
        private val progressBar = binding.progressBar // Reference to the ProgressBar

        fun bind(item: String, price: String, imageUrl: String) {
            binding.foodNamePopular.text = item
            binding.PricePopular.text = price

            // Show the ProgressBar while loading the image
            progressBar.visibility = View.VISIBLE

            // Load image from URL using Picasso
            Picasso.get()
                .load("https://food-app-backend-green.vercel.app/$imageUrl")
                .fit() // Scales image to fit within the view
                .centerCrop()  // Center crops the image
                .error(R.color.black)  // Displayed on error
                .into(imageView, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        // Hide ProgressBar once the image is loaded
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        // Hide ProgressBar in case of an error as well
                        progressBar.visibility = View.GONE
                    }
                })
        }
    }

}
