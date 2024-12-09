package com.pet.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pet.database.converter.Converters
import com.pet.database.dao.ArticleDao
import com.pet.database.entity.ArticleEntity
import com.pet.database.entity.SourceEntity

@Database(
    entities = [
        ArticleEntity::class,
        SourceEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "news_app_database"

        internal fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}