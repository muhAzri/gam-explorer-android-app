package com.zrifapps.favourite.data.manager


import com.zrifapps.core.common.manager.FavouriteGameManager
import com.zrifapps.core.domain.model.CoreFavouriteGame
import com.zrifapps.favourite.common.mapper.toFavouriteGame
import com.zrifapps.favourite.domain.repository.FavouriteGameRepository
import javax.inject.Inject

class FavouriteGameManagerImpl @Inject constructor(
    private val favouriteGameRepository: FavouriteGameRepository,
) : FavouriteGameManager {

    override suspend fun isFavourite(gameId: Int): Boolean {
        return favouriteGameRepository.isFavourite(gameId)
    }

    override suspend fun addToFavourites(coreFavouriteGame: CoreFavouriteGame) {
        return favouriteGameRepository.addToFavourites(game = coreFavouriteGame.toFavouriteGame())
    }

    override suspend fun removeFromFavourites(coreFavouriteGame: CoreFavouriteGame) {
        return favouriteGameRepository.removeFromFavourites(game = coreFavouriteGame.toFavouriteGame())
    }


}
