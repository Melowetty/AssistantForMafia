package com.sadteam.assistantformafia.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    private val DB_NAME = "app_database.db"

    @Singleton
    @Provides
    fun provideRolesDao(appDatabase: AppDatabase): RolesDao = appDatabase.getRolesDao()

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext appContext: Context,
        provider: Provider<RolesDao>
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DB_NAME,
        )
            .addCallback(RolesCallback(provider))
            .build()
    }
}