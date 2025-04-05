package com.zrifapps.game.data.request

data class GetGameRequest(
    val page: Int = 1,
    val pageSize: Int = 10,
    val search: String? = null,
)
