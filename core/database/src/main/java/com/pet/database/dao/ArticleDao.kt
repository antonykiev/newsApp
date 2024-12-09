package com.pet.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pet.database.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles WHERE id = :articleId")
    suspend fun article(articleId: Long): ArticleEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<ArticleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: ArticleEntity)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<ArticleEntity>

    @Query("SELECT * FROM articles")
    fun observeArticles(): Flow<List<ArticleEntity>>

    @Delete
    suspend fun deleteUser(user: ArticleEntity)

}