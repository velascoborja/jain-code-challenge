package com.akansha.digitalsurgery.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.akansha.digitalsurgery.datastorage.ProcedureDao
import com.akansha.digitalsurgery.model.ProcedureDetailResult
import com.akansha.digitalsurgery.model.ProcedureItem
import com.akansha.digitalsurgery.model.ProcedureListResult
import com.akansha.digitalsurgery.networking.ProcedureRetrofitService
import com.akansha.digitalsurgery.repository.mapper.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProcedureRepository @Inject constructor(
    private val service: ProcedureRetrofitService,
    private val dao: ProcedureDao,
) : IProcedureRepository {

    private val proceduresLiveData by lazy {
        MutableLiveData<ProcedureListResult>()
    }

    private val procedureDetailsLiveData by lazy {
        MutableLiveData<ProcedureDetailResult>()
    }

    private val favouritesLiveData by lazy {
        MutableLiveData<ProcedureListResult>()
    }

    override suspend fun getProcedures(): MutableLiveData<ProcedureListResult> {

        return withContext(Dispatchers.IO) {
            try {
                val response = service.getProcedures()
                if (response.isSuccessful) {
                    response.body()?.let {
                        proceduresLiveData.postValue(ProcedureListResult.Success(it.map()))
                    } ?: proceduresLiveData.postValue(ProcedureListResult.Failure)
                } else {
                    proceduresLiveData.postValue(ProcedureListResult.Failure)
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                proceduresLiveData.postValue(ProcedureListResult.Failure)
            }

            return@withContext proceduresLiveData
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getProcedureDetails(procedureId: String): MutableLiveData<ProcedureDetailResult> {

        return withContext(Dispatchers.IO) {
            try {
                val response = service.getProcedureDetails(procedureId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        procedureDetailsLiveData.postValue(ProcedureDetailResult.Success(it.map()))
                    } ?: procedureDetailsLiveData.postValue(ProcedureDetailResult.Failure)
                } else {
                    procedureDetailsLiveData.postValue(ProcedureDetailResult.Failure)
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                procedureDetailsLiveData.postValue(ProcedureDetailResult.Failure)
            }

            return@withContext procedureDetailsLiveData
        }
    }

    override suspend fun getFavouriteProcedures(): MutableLiveData<ProcedureListResult> {

        return withContext(Dispatchers.IO) {
            try {
                val response = dao.getFavouriteProcedures()
                favouritesLiveData.postValue(ProcedureListResult.Success(response))
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                favouritesLiveData.postValue(ProcedureListResult.Failure)
            }

            return@withContext favouritesLiveData
        }
    }

    override suspend fun saveFavouriteProcedure(procedure: ProcedureItem) {
        withContext(Dispatchers.IO) {
            try {
                dao.saveAsFavourite(procedure)
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }

    override suspend fun removeFavouriteProcedure(procedure: ProcedureItem) {
        withContext(Dispatchers.IO) {
            try {
                dao.removeAsFavourite(procedure)
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
}