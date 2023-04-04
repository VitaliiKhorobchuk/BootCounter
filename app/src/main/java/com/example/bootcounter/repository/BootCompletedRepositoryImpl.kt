package com.example.bootcounter.repository

import com.example.bootcounter.db.BootCounterDao
import com.example.bootcounter.db.BootCounterEntity
import kotlinx.coroutines.flow.Flow

class BootCompletedRepositoryImpl(private val bootCounterDao: BootCounterDao): BootCompleteRepository {

    override suspend fun insert(bootCounterEntity: BootCounterEntity) {
        bootCounterDao.insert(bootCounterEntity)
    }

    override suspend fun getAll(): List<BootCounterEntity> {
        return bootCounterDao.getAll()
    }

    override suspend fun getRecordsList(): Flow<List<BootCounterEntity>> {
        return bootCounterDao.getAllAsFlow()
    }
}