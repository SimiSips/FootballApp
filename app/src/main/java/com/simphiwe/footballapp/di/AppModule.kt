package com.simphiwe.footballapp.di

import com.simphiwe.footballapp.BuildConfig
import com.simphiwe.footballapp.constants.constants.BASE_URL
import com.simphiwe.footballapp.data.api.AuthenticationInterceptor
import com.simphiwe.footballapp.data.api.PlayerApi
import com.simphiwe.footballapp.repository.players.PlayerRepository
import com.simphiwe.footballapp.repository.players.PlayerRepositoryImpl
import com.simphiwe.footballapp.repository.teams.TeamsRepository
import com.simphiwe.footballapp.repository.teams.TeamsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(AuthenticationInterceptor())
        builder.retryOnConnectionFailure(false)
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        return builder.build()
    }

    @Provides
    fun providesFootballApi(okHttpClient: OkHttpClient): PlayerApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(PlayerApi::class.java)

    @Provides
    fun providesPlayerRepository(api: PlayerApi): PlayerRepository = PlayerRepositoryImpl(api)

    @Provides
    fun providesSearchRepository(api: PlayerApi): TeamsRepository = TeamsRepositoryImpl(api)
}