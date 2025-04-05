package com.zrifapps.game.di

import com.zrifapps.game.data.datasources.remote.GameApi
import com.zrifapps.game.data.repository.GameRepositoryImpl
import com.zrifapps.game.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GameModule {

    @Provides
    @Singleton
    fun provideGameApi(retrofit: Retrofit): GameApi {
        return retrofit.create(GameApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGameRepository(gameApi: GameApi): GameRepository {
        return GameRepositoryImpl(gameApi = gameApi)
    }
}
