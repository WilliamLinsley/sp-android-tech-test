package com.github.williamlinsley.scottishpowertechtest.ui.posts.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.williamlinsley.scottishpowertechtest.data.remote.PostDto

@Composable
fun PostsList(
    posts: List<PostDto>,
    onPostClick: (PostDto) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(posts) { post ->
            PostRow(
                post = post,
                onClick = { onPostClick(post) }
            )
        }
    }
}
