package com.sadteam.assistantformafia.data.repository

import androidx.lifecycle.LiveData
import com.sadteam.assistantformafia.data.db.RolesDao
import com.sadteam.assistantformafia.data.models.Role

class RoleRepository(private val rolesDao: RolesDao) {

    fun getAllRoles(): LiveData<List<Role>> = rolesDao.getAllRoles()

    suspend fun insertRole(role: Role) = rolesDao.insertRole(role = role)

    suspend fun updateRole(role: Role) = rolesDao.updateRole(role = role)

    suspend fun deleteRole(role: Role) = rolesDao.deleteRole(role = role)

    suspend fun deleteRoleById(id: Int) = rolesDao.deleteRoleById(id = id)

    suspend fun clearRoles() = rolesDao.clearRoles()
}