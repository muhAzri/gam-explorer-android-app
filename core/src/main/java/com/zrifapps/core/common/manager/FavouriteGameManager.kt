package com.zrifapps.core.common.manager

import com.zrifapps.core.domain.model.CoreFavouriteGame

interface FavouriteGameManager {
    suspend fun isFavourite(gameId: Int): Boolean
    suspend fun addToFavourites(coreFavouriteGame: CoreFavouriteGame)
    suspend fun removeFromFavourites(coreFavouriteGame: CoreFavouriteGame)
}
