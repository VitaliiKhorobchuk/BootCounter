package com.example.bootcounter.repository

import com.example.bootcounter.db.BootCounterEntity
import kotlinx.coroutines.flow.Flow

interface BootCompleteRepository {

    suspend fun insert(bootCounterEntity: BootCounterEntity)

    suspend fun getAll(): List<BootCounterEntity>

    suspend fun getRecordsList(): Flow<List<BootCounterEntity>>
}