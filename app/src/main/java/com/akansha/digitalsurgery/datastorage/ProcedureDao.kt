package com.akansha.digitalsurgery.datastorage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.akansha.digitalsurgery.model.ProcedureDetailCard
import com.akansha.digitalsurgery.model.ProcedureItem

@Dao
interface ProcedureDao {
    @Query("SELECT * FROM procedures")
    fun getFavouriteProcedures(): List<ProcedureItem>

    @Query("SELECT * FROM procedure_details WHERE id=:procedureId LIMIT 1")
    fun getProcedureDetail(procedureId: String): ProcedureDetailCard

    @Insert
    fun saveAsFavourite(procedure: ProcedureItem)

    @Delete
    fun removeAsFavourite(procedure: ProcedureItem)
}