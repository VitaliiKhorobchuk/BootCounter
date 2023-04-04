package com.example.bootcounter.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boot_counter")
data class BootCounterEntity(
    val timestamp: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}