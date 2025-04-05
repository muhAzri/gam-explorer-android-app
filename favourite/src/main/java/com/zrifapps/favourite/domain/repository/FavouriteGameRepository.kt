package com.zrifapps.favourite.domain.repository

import com.zrifapps.favourite.domain.entity.FavouriteGame
import kotlinx.coroutines.flow.Flow

interface FavouriteGameRepository {
    fun getAll(): Flow<List<FavouriteGame>>

    suspend fun isFavourite(gameId: Int): Boolean

    suspend fun addToFavourites(game: FavouriteGame)

    suspend fun removeFromFavourites(game: FavouriteGame)
}
