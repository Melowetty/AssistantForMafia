package com.sadteam.assistantformafia.data.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sadteam.assistantformafia.data.START_SET_ROLES
import com.sadteam.assistantformafia.data.models.Possibility
import com.sadteam.assistantformafia.data.models.Role
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class RolesCallback(
    private val provider: Provider<RolesDao>
): RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            prepopulateRoles()
        }
    }

    private suspend fun prepopulateRoles() {
        provider.get().insertRoles(
            START_SET_ROLES
        )
    }
}