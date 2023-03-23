package com.sadteam.assistantformafia.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sadteam.assistantformafia.data.models.Role

@Dao
interface RolesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRole(role: Role)

    @Delete
    suspend fun deleteRole(role: Role)

    @Update
    suspend fun updateRole(role: Role)

    @Query("SELECT * FROM roles order by id ASC")
    fun getAllRoles(): List<Role>

    @Query("DELETE FROM roles")
    suspend fun clearRoles()

    @Query("DELETE FROM roles WHERE id = :id")
    suspend fun deleteRoleById(id: Int)
}