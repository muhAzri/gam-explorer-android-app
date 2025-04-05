package com.zrifapps.gameexplorer.di

import com.zrifapps.core.config.ConfigProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.zrifapps.gameexplorer.BuildConfig
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ConfigProviderModule {

    @Provides
    @Singleton
    fun provideConfigProvider(): ConfigProvider {
        return ConfigProvider(
            debug = BuildConfig.DEBUG,
            baseUrl = BuildConfig.BASE_URL,
            apiKey = BuildConfig.API_KEY
        )
    }
}
