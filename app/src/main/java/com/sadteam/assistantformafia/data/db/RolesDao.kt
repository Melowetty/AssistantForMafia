package com.sadteam.assistantformafia.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sadteam.assistantformafia.data.models.entities.DbRole

@Dao
interface RolesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRole(role: DbRole)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoles(dbRoles: List<DbRole>)

    @Delete
    suspend fun deleteRole(dbRole: DbRole)

    @Update
    suspend fun updateRole(dbRole: DbRole)

    @Query("SELECT * FROM roles order by id ASC")
    fun getAllRoles(): LiveData<List<DbRole>>

    @Query("DELETE FROM roles")
    suspend fun clearRoles()

    @Query("DELETE FROM roles WHERE id = :id")
    suspend fun deleteRoleById(id: Int)
}