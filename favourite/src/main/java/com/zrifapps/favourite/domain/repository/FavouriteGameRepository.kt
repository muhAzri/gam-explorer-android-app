package com.zrifapps.favourite.domain.repository

import com.zrifapps.favourite.domain.entity.FavouriteGame

interface FavouriteGameRepository {
    suspend fun getAll(): List<FavouriteGame>

    suspend fun isFavourite(gameId: Int): Boolean

    suspend fun addToFavourites(game: FavouriteGame)

    suspend fun removeFromFavourites(game: FavouriteGame)
}
