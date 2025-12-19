package com.github.williamlinsley.scottishpowertechtest.data.repository

import com.github.williamlinsley.scottishpowertechtest.data.remote.PostDto
import com.github.williamlinsley.scottishpowertechtest.data.remote.PostsApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class PostsRepositoryTest {

    private val fakeApi = object : PostsApi {
        override suspend fun getPosts(): List<PostDto> {
            return listOf(
                PostDto(
                    userId = 1,
                    id = 1,
                    title = "banana",
                    body = "body"
                ),
                PostDto(
                    userId = 1,
                    id = 2,
                    title = "Apple",
                    body = "body"
                ),
                PostDto(
                    userId = 1,
                    id = 3,
                    title = "cherry",
                    body = "body"
                )
            )
        }
    }

    @Test
    fun `getPosts returns posts sorted alphabetically ignoring case`() = runBlocking {
        // GIVEN
        val repository = PostsRepository(fakeApi)

        // WHEN
        val result = repository.getPosts()

        // THEN
        val titles = result.map { it.title }
        assertEquals(listOf("Apple", "banana", "cherry"), titles)
    }

    @Test
    fun `getPosts returns empty list when api returns no posts`() {
        runBlocking {
            // GIVEN
            val emptyApi = object : PostsApi {
                override suspend fun getPosts(): List<PostDto> = emptyList()
            }
            val repository = PostsRepository(emptyApi)

            // WHEN
            val result = repository.getPosts()

            // THEN
            assertEquals(emptyList<PostDto>(), result)
        }
    }


}