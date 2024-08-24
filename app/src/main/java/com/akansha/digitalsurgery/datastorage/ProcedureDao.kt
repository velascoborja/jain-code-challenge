package com.akansha.digitalsurgery.datastorage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akansha.digitalsurgery.model.ProcedureItem

@Dao
interface ProcedureDao {
    @Query("SELECT * FROM procedures")
    suspend fun getFavouriteProcedures(): List<ProcedureItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAsFavourite(procedure: ProcedureItem)

    @Delete
    suspend fun removeAsFavourite(procedure: ProcedureItem)
}