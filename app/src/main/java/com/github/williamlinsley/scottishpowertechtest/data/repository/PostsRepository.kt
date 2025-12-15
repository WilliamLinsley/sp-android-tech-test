package com.github.williamlinsley.scottishpowertechtest.data.repository

import com.github.williamlinsley.scottishpowertechtest.data.remote.PostDto
import com.github.williamlinsley.scottishpowertechtest.data.remote.PostsApi
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postsApi: PostsApi
) {

    suspend fun getPosts(): List<PostDto> {
        return postsApi
            .getPosts()
            .sortedBy { it.title.lowercase() }
    }
}
