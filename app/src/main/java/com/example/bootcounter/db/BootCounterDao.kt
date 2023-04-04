package com.example.bootcounter.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BootCounterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bootCounterEntity: BootCounterEntity)

    @Query("SELECT * FROM boot_counter")
    suspend fun getAll(): List<BootCounterEntity>

    @Query("SELECT * FROM boot_counter")
    fun getAllAsFlow(): Flow<List<BootCounterEntity>>

}