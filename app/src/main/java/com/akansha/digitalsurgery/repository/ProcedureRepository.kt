package com.akansha.digitalsurgery.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.akansha.digitalsurgery.model.ProcedureDetailResult
import com.akansha.digitalsurgery.model.ProcedureListResult
import com.akansha.digitalsurgery.networking.ProcedureRetrofitService
import com.akansha.digitalsurgery.repository.mapper.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProcedureRepository @Inject constructor(
    private val service: ProcedureRetrofitService,
) : IProcedureRepository {

    private val procedureLiveData by lazy {
        MutableLiveData<ProcedureListResult>()
    }

    private val procedureDetailLiveData by lazy {
        MutableLiveData<ProcedureDetailResult>()
    }

    override suspend fun getProcedures(): MutableLiveData<ProcedureListResult> {

        return withContext(Dispatchers.IO) {
            try {
                val response = service.getProcedures()

                if (response.isSuccessful) {
                    response.body()?.let {
                        procedureLiveData.postValue(ProcedureListResult.Success(it.map()))
                    } ?: procedureLiveData.postValue(ProcedureListResult.Failure)
                } else {
                    procedureLiveData.postValue(ProcedureListResult.Failure)
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                procedureLiveData.postValue(ProcedureListResult.Failure)
            }

            return@withContext procedureLiveData
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getProcedureDetails(procedureId: String): MutableLiveData<ProcedureDetailResult> {
        return withContext(Dispatchers.IO) {
            try {

                val response = service.getProcedureDetails(procedureId)

                if (response.isSuccessful) {
                    response.body()?.let {
                        procedureDetailLiveData.postValue(ProcedureDetailResult.Success(it.map()))
                    } ?: procedureDetailLiveData.postValue(ProcedureDetailResult.Failure)
                } else {
                    procedureDetailLiveData.postValue(ProcedureDetailResult.Failure)
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                procedureDetailLiveData.postValue(ProcedureDetailResult.Failure)
            }

            return@withContext procedureDetailLiveData
        }
    }
}