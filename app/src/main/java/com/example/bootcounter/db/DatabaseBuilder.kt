package com.example.bootcounter.db

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            BootCounterDB::class.java,
            "boot-counter-db"
        ).build()
}
