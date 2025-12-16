package com.github.williamlinsley.scottishpowertechtest.ui.navigation

object Routes {
    const val POSTS = "posts"
    const val POST_DETAILS = "post_details/{postId}"

    fun postDetails(postId: Int) = "post_details/$postId"
}
