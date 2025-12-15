package com.github.williamlinsley.scottishpowertechtest.data.remote

import retrofit2.http.GET

interface PostsApi {

    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}
