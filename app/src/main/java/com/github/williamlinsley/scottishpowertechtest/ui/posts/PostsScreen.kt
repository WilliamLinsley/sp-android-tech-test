package com.github.williamlinsley.scottishpowertechtest.ui.posts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.williamlinsley.scottishpowertechtest.data.remote.PostDto
import com.github.williamlinsley.scottishpowertechtest.ui.posts.components.ErrorState
import com.github.williamlinsley.scottishpowertechtest.ui.posts.components.LoadingState
import com.github.williamlinsley.scottishpowertechtest.ui.posts.components.PostsList

@Composable
fun PostsScreen(
    onPostClick: (PostDto) -> Unit,
    viewModel: PostsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            LoadingState()
        }
        uiState.error != null -> {
            ErrorState(uiState.error)
        }
        else -> {
            PostsList(
                posts = uiState.posts,
                onPostClick = onPostClick
            )
        }
    }
}
