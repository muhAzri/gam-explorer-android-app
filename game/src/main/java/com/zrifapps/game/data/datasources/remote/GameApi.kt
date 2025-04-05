package com.zrifapps.game.data.datasources.remote

import com.zrifapps.core.response.BaseResponse
import com.zrifapps.game.data.dto.GameDTO
import retrofit2.http.GET
import retrofit2.http.Query


interface GameApi {

    @GET("games")
    suspend fun games(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("search") search: String?,
    ): BaseResponse<List<GameDTO>>
}
