package com.github.williamlinsley.scottishpowertechtest.ui.posts

import com.github.williamlinsley.scottishpowertechtest.data.remote.PostDto
import com.github.williamlinsley.scottishpowertechtest.data.remote.PostsApi
import com.github.williamlinsley.scottishpowertechtest.data.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PostsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState contains posts when repository succeeds`() = runTest {
        // GIVEN
        val fakeApi = object : PostsApi {
            override suspend fun getPosts(): List<PostDto> {
                return listOf(
                    PostDto(
                        userId = 1,
                        id = 1,
                        title = "Title",
                        body = "Body"
                    )
                )
            }
        }

        val repository = PostsRepository(fakeApi)
        val viewModel = PostsViewModel(repository)

        // WHEN (viewmodel has finished loading posts)
        testDispatcher.scheduler.advanceUntilIdle()

        // THEN
        val state = viewModel.uiState.value
        assertEquals(1, state.posts.size)
        assertNull(state.error)
    }

    @Test
    fun `uiState contains error when repository throws exception`() = runTest {
        // GIVEN
        val fakeApi = object : PostsApi {
            override suspend fun getPosts(): List<PostDto> {
                throw RuntimeException("Network error")
            }
        }

        val repository = PostsRepository(fakeApi)
        val viewModel = PostsViewModel(repository)

        // WHEN (vm finished loading posts)
        testDispatcher.scheduler.advanceUntilIdle()

        // THEN
        val state = viewModel.uiState.value
        assertEquals("Failed to load posts: Network error", state.error)
        assertEquals(0, state.posts.size)
    }

    @Test
    fun `uiState is loading initially`() {
        // GIVEN
        val fakeApi = object : PostsApi {
            override suspend fun getPosts(): List<PostDto> = emptyList()
        }
        val repository = PostsRepository(fakeApi)

        // WHEN
        val viewModel = PostsViewModel(repository)

        // THEN
        val state = viewModel.uiState.value
        assertEquals(true, state.isLoading)
    }

}
