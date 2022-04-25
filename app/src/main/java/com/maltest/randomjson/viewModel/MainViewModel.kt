package com.maltest.randomjson.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maltest.randomjson.model.Post
import com.maltest.randomjson.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val allPost = MutableLiveData<ArrayList<Post>>()
    val delete = MutableLiveData<Post>()

     fun apiPostList(): LiveData<ArrayList<Post>> {
        RetrofitClient.service.list().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {
                allPost.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                allPost.value = null
            }
        })
        return allPost
    }

     fun apiPostDelete(post: Post): LiveData<Post> {
        RetrofitClient.service.delete(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                delete.value = response.body()
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                delete.value = null
            }
        })
        return delete
    }
}