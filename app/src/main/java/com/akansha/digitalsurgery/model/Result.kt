package com.akansha.digitalsurgery.model

sealed class Result {
    data class Success(val procedures: List<Procedure>) : Result()
    data object Failure : Result()
}