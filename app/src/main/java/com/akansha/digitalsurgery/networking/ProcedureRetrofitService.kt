package com.akansha.digitalsurgery.networking

import com.akansha.digitalsurgery.networking.model.Procedure
import com.akansha.digitalsurgery.networking.model.ProcedureDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProcedureRetrofitService {

    @GET("/api/v3/procedures")
    suspend fun getProcedures(): Response<List<Procedure>>

    @GET("/api/v3/procedures/{procedureId}")
    suspend fun getProcedureDetails(@Path("procedureId") procedureId: String): Response<ProcedureDetail>
}