package com.pet.database.di

import android.content.Context
import com.pet.database.AppDatabase
import com.pet.database.dao.ArticleDao
import com.pet.database.dao.QueryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideArticleDao(appDatabase: AppDatabase): ArticleDao {
        return appDatabase.articleDao()
    }

    @Provides
    fun provideQueryDao(appDatabase: AppDatabase): QueryDao {
        return appDatabase.queryDao()
    }
}