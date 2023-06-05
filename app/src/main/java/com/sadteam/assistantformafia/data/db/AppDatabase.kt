package com.sadteam.assistantformafia.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sadteam.assistantformafia.data.Converters
import com.sadteam.assistantformafia.data.models.entities.DbRole

@Database(entities = [DbRole::class], version = 7, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRolesDao(): RolesDao
}