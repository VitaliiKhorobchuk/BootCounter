package com.example.bootcounter.utils

import android.content.Context
import com.example.bootcounter.db.DatabaseBuilder
import com.example.bootcounter.repository.BootCompleteRepository
import com.example.bootcounter.repository.BootCompletedRepositoryImpl

object DependencyHelper {

    var bootCompleteRepository: BootCompleteRepository? = null

    fun provideBootCompletedRepository(context: Context): BootCompleteRepository {
        if (bootCompleteRepository == null) {
            val dao = DatabaseBuilder.buildRoomDB(context).bootCounterDao()
            bootCompleteRepository = BootCompletedRepositoryImpl(dao)
        }
        return bootCompleteRepository as BootCompletedRepositoryImpl
    }
}