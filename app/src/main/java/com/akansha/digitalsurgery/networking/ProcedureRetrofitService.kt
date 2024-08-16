package com.akansha.digitalsurgery.networking

import com.akansha.digitalsurgery.model.Procedure
import retrofit2.Response
import retrofit2.http.GET

interface ProcedureRetrofitService {

    @GET("/api/v3/procedures")
    suspend fun getProcedures(): Response<List<Procedure>>
}