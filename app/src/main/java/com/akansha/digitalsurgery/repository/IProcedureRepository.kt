package com.akansha.digitalsurgery.repository

import androidx.lifecycle.MutableLiveData
import com.akansha.digitalsurgery.model.Result

interface IProcedureRepository {

    suspend fun getProcedures(): MutableLiveData<Result>
}