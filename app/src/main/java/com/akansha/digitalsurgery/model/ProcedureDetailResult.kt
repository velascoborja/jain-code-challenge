package com.akansha.digitalsurgery.model

sealed class ProcedureDetailResult {
    data class Success(val details: ProcedureDetailCard) : ProcedureDetailResult()
    data object Failure : ProcedureDetailResult()
}