package com.github.williamlinsley.scottishpowertechtest.ui.posts

import com.github.williamlinsley.scottishpowertechtest.data.remote.PostDto

data class PostsUiState(
    val isLoading: Boolean = false,
    val posts: List<PostDto> = emptyList(),
    val error: String? = null
)
