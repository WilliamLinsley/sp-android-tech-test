package com.github.williamlinsley.scottishpowertechtest.ui.postdetails

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.williamlinsley.scottishpowertechtest.data.remote.PostDto

@Composable
fun PostDetailsScreen(
    post: PostDto
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = post.title,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = post.body,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
