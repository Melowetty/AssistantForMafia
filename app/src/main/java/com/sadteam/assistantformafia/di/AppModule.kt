package com.sadteam.assistantformafia.di

import android.content.Context
import android.content.SharedPreferences
import com.sadteam.assistantformafia.utils.SETTINGS_FILE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SETTINGS_FILE, Context.MODE_PRIVATE)
    }
}