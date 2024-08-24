package com.akansha.digitalsurgery.networking

import com.akansha.digitalsurgery.networking.model.Procedure
import com.akansha.digitalsurgery.networking.model.ProcedureDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProcedureRetrofitService {

    @GET(GET_PROCEDURES_ENDPOINT)
    suspend fun getProcedures(): Response<List<Procedure>>

    @GET(GET_PROCEDURE_DETAILS_ENDPOINT)
    suspend fun getProcedureDetails(@Path("procedureId") procedureId: String): Response<ProcedureDetail>
}

private const val GET_PROCEDURES_ENDPOINT = "/api/v3/procedures"
private const val GET_PROCEDURE_DETAILS_ENDPOINT = "$GET_PROCEDURES_ENDPOINT/{procedureId}"