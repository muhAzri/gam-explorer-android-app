package com.zrifapps.gameexplorer.di

import com.zrifapps.core.common.manager.ConfigManager
import com.zrifapps.gameexplorer.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigProviderModule {

    @Provides
    @Singleton
    fun provideConfigManager(): ConfigManager {
        return ConfigManager(
            debug = BuildConfig.DEBUG,
            baseUrl = BuildConfig.BASE_URL,
            apiKey = BuildConfig.API_KEY
        )
    }
}
