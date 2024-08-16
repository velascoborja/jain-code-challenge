package com.akansha.digitalsurgery.model

sealed class Result {
    data class Success(val procedures: List<ProcedureItem>) : Result()
    data object Failure : Result()
}