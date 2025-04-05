package com.zrifapps.game.domain.repository

import com.zrifapps.core.network.NetworkResult
import com.zrifapps.game.data.request.GetGameRequest
import com.zrifapps.game.domain.entity.Game

interface GameRepository {

    suspend fun getGame(getGameRequest: GetGameRequest): NetworkResult<List<Game>>
}
