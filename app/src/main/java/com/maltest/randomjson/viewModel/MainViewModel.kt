package com.maltest.randomjson.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maltest.randomjson.model.Post
import com.maltest.randomjson.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val allPost = MutableLiveData<ArrayList<Post>>()
    val deletePost = MutableLiveData<Post>()

     fun apiPostList(): LiveData<ArrayList<Post>> {
        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
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
        RetrofitHttp.postService.deletePost(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                deletePost.value = response.body()
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                deletePost.value = null
            }
        })
        return deletePost
    }
}