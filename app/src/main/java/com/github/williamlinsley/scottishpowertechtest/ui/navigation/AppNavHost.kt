package com.github.williamlinsley.scottishpowertechtest.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.github.williamlinsley.scottishpowertechtest.ui.components.AppTopBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.williamlinsley.scottishpowertechtest.ui.posts.PostsScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.github.williamlinsley.scottishpowertechtest.ui.postdetails.PostDetailsScreen
import com.github.williamlinsley.scottishpowertechtest.ui.posts.PostsViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.collectAsState


@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            AppTopBar(
                title = if (currentRoute?.startsWith("post_details") == true) {
                    "Post Details"
                } else {
                    "Posts"
                },
                showBack = currentRoute?.startsWith("post_details") == true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Routes.POSTS,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(Routes.POSTS) {
                val viewModel: PostsViewModel = hiltViewModel()

                PostsScreen(
                    onPostClick = { post ->
                        navController.navigate(Routes.postDetails(post.id))
                    },
                    viewModel = viewModel
                )
            }

            composable(
                route = Routes.POST_DETAILS,
                arguments = listOf(navArgument("postId") { type = NavType.IntType })
            ) { backStackEntry ->
                val viewModel: PostsViewModel = hiltViewModel()

                val uiState by viewModel.uiState.collectAsState()
                val postId = backStackEntry.arguments?.getInt("postId")

                val post = uiState.posts.firstOrNull { it.id == postId }

                post?.let {
                    PostDetailsScreen(post = it)
                }
            }
        }
    }
}
