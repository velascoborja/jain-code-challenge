package com.akansha.digitalsurgery.model

sealed class ProcedureListResult {
    data class Success(val procedures: List<ProcedureItem>) : ProcedureListResult()
    data object Failure : ProcedureListResult()
}