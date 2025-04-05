package com.zrifapps.favourite.di

import com.zrifapps.core.common.manager.FavouriteGameManager
import com.zrifapps.favourite.data.datasources.local.FavouriteGameDao
import com.zrifapps.favourite.data.manager.FavouriteGameManagerImpl
import com.zrifapps.favourite.data.repository.FavouriteGameRepositoryImpl
import com.zrifapps.favourite.domain.repository.FavouriteGameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteGameModule {

    @Provides
    @Singleton
    fun provideFavouriteGameRepository(favouriteGameDao: FavouriteGameDao): FavouriteGameRepository {
        return FavouriteGameRepositoryImpl(favouriteGameDao)
    }

    @Provides
    @Singleton
    fun provideFavouriteGameManager(favouriteGameRepository: FavouriteGameRepository): FavouriteGameManager {
        return FavouriteGameManagerImpl(favouriteGameRepository)
    }

}
