package com.sadteam.assistantformafia.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.data.Converters

@Database(entities = [Role::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRolesDao(): RolesDao
}