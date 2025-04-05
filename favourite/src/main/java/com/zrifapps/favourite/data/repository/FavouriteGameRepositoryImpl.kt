package com.zrifapps.favourite.data.repository

import com.zrifapps.favourite.data.datasources.local.FavouriteGameDao
import com.zrifapps.favourite.domain.entity.FavouriteGame
import com.zrifapps.favourite.domain.repository.FavouriteGameRepository
import javax.inject.Inject


class FavouriteGameRepositoryImpl @Inject constructor(
    private val favouriteGameDao: FavouriteGameDao,
) : FavouriteGameRepository {

    override suspend fun getAll(): List<FavouriteGame> {
        return favouriteGameDao.getAll()
    }

    override suspend fun isFavourite(gameId: Int): Boolean {
        return favouriteGameDao.isFavourite(gameId)
    }

    override suspend fun addToFavourites(game: FavouriteGame) {
        favouriteGameDao.insert(game)
    }

    override suspend fun removeFromFavourites(game: FavouriteGame) {
        favouriteGameDao.delete(game)
    }
}
