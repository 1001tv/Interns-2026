package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDataDao {
    @Query("SELECT * FROM app_data WHERE id = 1 LIMIT 1")
    fun getAppData(): Flow<AppDataEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(entity: AppDataEntity)

    @Query("DELETE FROM app_data")
    suspend fun deleteAll()
}
