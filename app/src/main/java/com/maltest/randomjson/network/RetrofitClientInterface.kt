package com.maltest.randomjson.network

import com.maltest.randomjson.model.Post
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RetrofitClientInterface {

    @Headers(
        "Content-type:application/json"
    )

    @GET("posts")
    fun list(): Call<ArrayList<Post>>

    @DELETE("posts/{id}")
    fun delete(@Path("id") id: Int): Call<Post>

}