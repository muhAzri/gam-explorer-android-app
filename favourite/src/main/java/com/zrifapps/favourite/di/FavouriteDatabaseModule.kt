package com.zrifapps.favourite.di

import android.content.Context
import androidx.room.Room
import com.zrifapps.favourite.data.datasources.local.FavouriteDatabase
import com.zrifapps.favourite.data.datasources.local.FavouriteGameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): FavouriteDatabase {
        return Room.databaseBuilder(
            appContext,
            FavouriteDatabase::class.java,
            "favourite_games.db"
        ).build()
    }

    @Provides
    fun provideFavouriteGameDao(db: FavouriteDatabase): FavouriteGameDao {
        return db.favouriteGameDao()
    }
}
