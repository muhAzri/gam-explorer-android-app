package com.zrifapps.game.data.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zrifapps.core.network.NetworkResult
import com.zrifapps.game.data.request.GetGameRequest
import com.zrifapps.game.domain.entity.Game
import com.zrifapps.game.domain.repository.GameRepository

class GamePagingSource(
    private val gameRepository: GameRepository,
    private val query: String,
) : PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX

            val response = gameRepository.getGame(
                GetGameRequest(
                    page = page,
                    pageSize = params.loadSize,
                    search = query
                )
            )

            when (response) {
                is NetworkResult.Success -> {
                    val games = response.data
                    LoadResult.Page(
                        data = games,
                        prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                        nextKey = if (games.isEmpty()) null else page + 1
                    )
                }

                is NetworkResult.Error -> {
                    LoadResult.Error(Exception(response.message))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}
