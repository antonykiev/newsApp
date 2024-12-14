package com.petproject.core.di

import com.pet.database.dao.ArticleDao
import com.pet.database.dao.QueryDao
import com.pet.database.source.ArticleLocalDataSource
import com.pet.database.source.QueryLocalDataSource
import com.petproject.core.repository.ArticleRepository
import com.petproject.core.repository.ArticleRepositoryImpl
import com.petproject.core.repository.QueryRepository
import com.petproject.core.repository.QueryRepositoryImpl
import com.petproject.core.repository.source.ArticleLocalDataSourceImpl
import com.petproject.core.repository.source.EverythingDataSourceImpl
import com.petproject.core.repository.source.QueryLocalDataSourceImpl
import com.petproject.server.api.RemoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiModule {

    @Provides
    @Singleton
    fun providesEverythingDataSource(remoteApi: RemoteApi): EverythingDataSourceImpl {
        return EverythingDataSourceImpl(
            api = remoteApi
        )
    }

    @Provides
    @Singleton
    fun providesArticleLocalDataSource(articleDao: ArticleDao): ArticleLocalDataSource {
        return ArticleLocalDataSourceImpl(
            articleDao = articleDao
        )
    }

    @Provides
    @Singleton
    fun providesQueryLocalDataSource(queryDao: QueryDao): QueryLocalDataSource {
        return QueryLocalDataSourceImpl(
            queryDao = queryDao
        )
    }

    @Provides
    @Singleton
    fun providesServerRepository(
        everythingDataSource: EverythingDataSourceImpl,
        articleLocalDataSource: ArticleLocalDataSource,
    ): ArticleRepository {
        return ArticleRepositoryImpl(
            dataSourceRemote = everythingDataSource,
            dataSourceLocal = articleLocalDataSource
        )
    }

    @Provides
    @Singleton
    fun providesQueryRepository(
        queryLocalDataSource: QueryLocalDataSource,
    ): QueryRepository {
        return QueryRepositoryImpl(
            queryLocalDataSource = queryLocalDataSource,
        )
    }
}