package com.pet.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pet.database.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles WHERE id = :articleId")
    suspend fun article(articleId: Long): ArticleEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(articles: List<ArticleEntity>): List<Long>

    @Update
    suspend fun update(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE keywords LIKE '%' || :keyword || '%'")
    fun observeArticles(keyword: String): Flow<List<ArticleEntity>>

    @Delete
    suspend fun deleteUser(user: ArticleEntity)

}