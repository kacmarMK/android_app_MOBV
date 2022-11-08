package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PubDAO {
    @Query("SELECT * FROM pub_table")
    fun getPubs(): Flow<List<Pub>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pub: Pub)

    @Delete
    suspend fun delete(pub: Pub)

    @Query("DELETE FROM pub_table")
    suspend fun deleteAll()
}