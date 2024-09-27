package com.petproject.server.di

import com.petproject.server.ServerRepository
import com.petproject.server.ServerRepositoryImpl
import com.petproject.server.api.RemoteApi
import com.petproject.server.source.EverythingDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideRemoteApi(): RemoteApi = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .apply {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val authInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("Authorization", "7c47f4503d364c32b088382b6950cfa1")
                    .build()
                return@Interceptor it.proceed(request)
            }
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()
            client(okHttpClient)
        }
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RemoteApi::class.java)

    @Provides
    @Singleton
    fun providesEverythingDataSource(remoteApi: RemoteApi): EverythingDataSource {
        return EverythingDataSource(remoteApi)
    }

    @Provides
    @Singleton
    fun providesServerRepository(everythingDataSource: EverythingDataSource): ServerRepository {
        return ServerRepositoryImpl(everythingDataSource)
    }
}