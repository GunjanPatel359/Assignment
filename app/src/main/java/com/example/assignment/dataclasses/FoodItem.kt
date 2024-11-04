package com.example.assignment.dataclasses

data class FoodItem(
    val reviewCount: ReviewCount,
    val _id: String,
    val name: String,
    val imageUrl: String,
    val smallDescription: String,
    val description: String,
    val veg: Boolean,
    val price: Int,
    val foodTypes: List<String>,
    val order: Int,
    val foodCategoryId: String,
    val restaurantId: String,
    val avgreview: Int,
    val totalReview: Int
) {

}

data class ReviewCount(
    val `1`: Int,
    val `2`: Int,
    val `3`: Int,
    val `4`: Int,
    val `5`: Int
)
