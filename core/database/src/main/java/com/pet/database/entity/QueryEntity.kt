package com.pet.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "queries")
data class QueryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val query: String,
    val timestamp: Long = System.currentTimeMillis()
)
