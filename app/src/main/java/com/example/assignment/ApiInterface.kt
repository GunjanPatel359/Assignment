package com.example.assignment

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("x-rapidapi-key: 29ad3057aamsh0ba75818e3fcb21p1ae2aajsn47551dd3f7a7","x-rapidapi-host : deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query:String): Call<List<MyData>>
}