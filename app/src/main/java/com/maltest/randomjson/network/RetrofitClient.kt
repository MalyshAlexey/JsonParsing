package com.maltest.randomjson.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val url = "https://jsonplaceholder.typicode.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    val service: RetrofitClientInterface = retrofit.create(RetrofitClientInterface::class.java)
}