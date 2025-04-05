package com.zrifapps.game.data.repository

import com.zrifapps.core.network.NetworkResult
import com.zrifapps.core.data.repository.BaseRepository
import com.zrifapps.game.common.mapper.toDomain
import com.zrifapps.game.data.datasources.remote.GameApi
import com.zrifapps.game.data.request.GetGameRequest
import com.zrifapps.game.domain.entity.Game
import com.zrifapps.game.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    val gameApi: GameApi,
) : GameRepository, BaseRepository() {
    override suspend fun getGame(getGameRequest: GetGameRequest): NetworkResult<List<Game>> {
        return safeApiCall {

            val response = gameApi.games(
                page = getGameRequest.page,
                pageSize = getGameRequest.pageSize,
                search = getGameRequest.search
            )

            response.results.toDomain()
        }
    }

    override suspend fun getGameDetail(gameId: String): NetworkResult<Game> {
        return safeApiCall {
            val response = gameApi.gamesDetail(gameId)

            response.toDomain()
        }
    }


}
