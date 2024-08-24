package com.akansha.digitalsurgery.repository

import androidx.lifecycle.MutableLiveData
import com.akansha.digitalsurgery.model.ProcedureDetailResult
import com.akansha.digitalsurgery.model.ProcedureItem
import com.akansha.digitalsurgery.model.ProcedureListResult

interface IProcedureRepository {

    suspend fun getProcedures(): MutableLiveData<ProcedureListResult>

    suspend fun getProcedureDetails(procedureId: String): MutableLiveData<ProcedureDetailResult>

    suspend fun getFavouriteProcedures(): MutableLiveData<ProcedureListResult>

    suspend fun saveFavouriteProcedure(procedure: ProcedureItem)

    suspend fun removeFavouriteProcedure(procedure: ProcedureItem)
}