package com.pet.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pet.database.entity.QueryEntity

@Dao
interface QueryDao {

    @Query("SELECT * FROM queries ORDER BY timestamp DESC LIMIT 10")
    suspend fun getRecentQueries(): List<QueryEntity>

    @Query("SELECT COUNT(*) FROM queries")
    suspend fun getRowCount(): Int

    @Query("DELETE FROM queries WHERE id = (SELECT id FROM queries ORDER BY timestamp ASC LIMIT 1)")
    suspend fun deleteOldestRow()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(query: QueryEntity)

    suspend fun saveQuery(query: QueryEntity) {
        if (getRowCount() >= 15) {
            deleteOldestRow()
        }
        insertQuery(query)
    }
}