package com.akansha.digitalsurgery.repository

import androidx.lifecycle.MutableLiveData
import com.akansha.digitalsurgery.model.ProcedureDetailResult
import com.akansha.digitalsurgery.model.ProcedureItem
import com.akansha.digitalsurgery.model.ProcedureListResult

class MockProcedureRepository : IProcedureRepository {

    var procedureListResult: ProcedureListResult = ProcedureListResult.Failure
    var procedureDetailResult: ProcedureDetailResult = ProcedureDetailResult.Failure
    var procedureLiveData: MutableLiveData<ProcedureListResult> = MutableLiveData()
    var procedureDetailLiveData: MutableLiveData<ProcedureDetailResult> = MutableLiveData()
    var favouritesLiveData: MutableLiveData<ProcedureListResult> = MutableLiveData()

    override suspend fun getProcedures(): MutableLiveData<ProcedureListResult> {
        procedureLiveData.postValue(procedureListResult)
        return procedureLiveData
    }

    override suspend fun getProcedureDetails(procedureId: String): MutableLiveData<ProcedureDetailResult> {
        procedureDetailLiveData.postValue(procedureDetailResult)
        return procedureDetailLiveData
    }

    override suspend fun getFavouriteProcedures(): MutableLiveData<ProcedureListResult> {
        favouritesLiveData.postValue(procedureListResult)
        return favouritesLiveData
    }

    override suspend fun saveFavouriteProcedure(procedure: ProcedureItem) {}

    override suspend fun removeFavouriteProcedure(procedure: ProcedureItem) {}
}