package com.akansha.digitalsurgery.repository

import androidx.lifecycle.MutableLiveData
import com.akansha.digitalsurgery.model.Result
import com.akansha.digitalsurgery.networking.ProcedureRetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import android.util.Log

class ProcedureRepository @Inject constructor(
    private val service: ProcedureRetrofitService,
) : IProcedureRepository {

    private val procedureLiveData by lazy {
        MutableLiveData<Result>()
    }

    override suspend fun getProcedures(): MutableLiveData<Result> {

        return withContext(Dispatchers.IO) {
            try {
                val response = service.getProcedures()

                if (response.isSuccessful) {
                    response.body()?.let {
                        procedureLiveData.postValue(Result.Success(it))
                    } ?: procedureLiveData.postValue(Result.Failure)
                } else {
                    procedureLiveData.postValue(Result.Failure)
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                procedureLiveData.postValue(Result.Failure)
            }

            return@withContext procedureLiveData
        }
    }
}