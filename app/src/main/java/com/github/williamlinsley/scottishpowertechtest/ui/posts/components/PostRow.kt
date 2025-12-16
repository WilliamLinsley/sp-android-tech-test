package com.github.williamlinsley.scottishpowertechtest.ui.posts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.williamlinsley.scottishpowertechtest.data.remote.PostDto

@Composable
fun PostRow(
    post: PostDto,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Text(
            text = post.title,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = post.body,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2
        )
    }
}
