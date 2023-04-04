package com.example.bootcounter.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BootCounterEntity::class], version = 1)
abstract class BootCounterDB: RoomDatabase() {

    abstract fun bootCounterDao(): BootCounterDao
}