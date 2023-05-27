package com.sadteam.assistantformafia.data.repository

import androidx.lifecycle.LiveData
import com.sadteam.assistantformafia.data.db.RolesDao
import com.sadteam.assistantformafia.data.models.entities.DbRole
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoleRepository @Inject constructor(
    private val rolesDao: RolesDao
    ) {
    fun getAllRoles(): LiveData<List<DbRole>> = rolesDao.getAllRoles()

    suspend fun insertRole(dbRole: DbRole) = rolesDao.insertRole(role = dbRole)

    suspend fun updateRole(dbRole: DbRole) = rolesDao.updateRole(dbRole = dbRole)

    suspend fun deleteRole(dbRole: DbRole) = rolesDao.deleteRole(dbRole = dbRole)

    suspend fun deleteRoleById(id: Int) = rolesDao.deleteRoleById(id = id)

    suspend fun clearRoles() = rolesDao.clearRoles()
}