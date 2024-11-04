package com.example.assignment.api

import com.example.assignment.dataclasses.FoodItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("api/v1/restaurant/food-items/search-food-items/search")
    fun getFoodItems(): Call<FoodItemsResponse>

    @GET("/api/v1/fooditem/{foodItemId}/get-food-item")
    fun getFoodItem(@Path("foodItemId") foodItemId: String): Call<FoodItemResponse>

//    @POST("api/sendData")
//    fun sendData(@Body data: DataRequest): Call<DataResponse>
}

// Data models for request and response
data class MessageResponse(val message: String)
data class DataRequest(val data: String)
data class DataResponse(val received: String)


data class FoodItemsResponse(
    val success: Boolean,
    val fooditem: List<FoodItem>
)

data class FoodItemResponse(
    val success: Boolean,
    val fooditem: FoodItem
)
