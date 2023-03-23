package com.sadteam.assistantformafia.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sadteam.assistantformafia.data.models.Possibility
import com.sadteam.assistantformafia.data.models.Role
import com.sadteam.assistantformafia.utils.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Role::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRolesDao(): RolesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private const val DB_NAME = "app_database.db"

        fun getDatabase(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME,
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(RolesCallback())
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private class RolesCallback(): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {appDatabase ->  
                CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {
                    appDatabase.getRolesDao().insertRole(
                        Role(
                            id = 1,
                            name = "Мафия",
                            description = "",
                            possibilities = listOf(Possibility.KILL),
                            max = 5,
                            icon = ""
                        )
                    )
                }
            }
        }
    }
}