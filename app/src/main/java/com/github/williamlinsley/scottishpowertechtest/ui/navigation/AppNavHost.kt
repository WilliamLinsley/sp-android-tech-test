package com.github.williamlinsley.scottishpowertechtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.williamlinsley.scottishpowertechtest.ui.posts.PostsScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.POSTS,
        modifier = modifier
    ) {
        composable(Routes.POSTS) {
            PostsScreen(
                onPostClick = { post ->
                    navController.navigate("${Routes.POST_DETAILS}/${post.id}")
                }
            )
        }

        // TODO...Details screen will go here next
    }
}
